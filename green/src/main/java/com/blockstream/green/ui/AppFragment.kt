package com.blockstream.green.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.blockstream.green.R
import com.blockstream.green.database.Wallet
import com.blockstream.green.gdk.SessionManager
import com.blockstream.green.gdk.getIcon
import com.greenaddress.greenapi.HWWallet
import com.greenaddress.greenapi.HWWalletBridge
import com.greenaddress.greenbits.ui.TabbedMainActivity
import javax.inject.Inject


/**
 * AppFragment
 *
 * This class is a useful abstract base class. Extend all other Fragments if possible.
 * Some of the features can be turned on/off in the constructor.
 *
 * It's crucial every AppFragment implementation to call @AndroidEntryPoint
 *
 * @property layout the layout id of the fragment
 * is called when the fragment is not actually visible
 *
 */

abstract class AppFragment<T : ViewDataBinding>(
    @LayoutRes val layout: Int,
    @MenuRes val menuRes: Int
) : Fragment(), HWWalletBridge {
    open val isAdjustResize = false

    internal lateinit var binding: T

    @Inject
    internal lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layout, container, false)
        binding.lifecycleOwner = this

        if (menuRes > 0) {
            setHasOptionsMenu(true)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(if (isAdjustResize) WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE else WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menuRes > 0) {
            inflater.inflate(menuRes, menu)
        }
    }

    protected fun closeDrawer() {
        (requireActivity() as AppActivity).closeDrawer()
    }

    protected fun isDrawerOpen() = (requireActivity() as AppActivity).isDrawerOpen()

    fun setToolbar(wallet: Wallet) {
        val icon = ContextCompat.getDrawable(requireContext(), wallet.getIcon())
        setToolbar(wallet.name, subtitle = null, drawable = icon)
    }

    fun setToolbar(title: String? = null, subtitle: String? = null, drawable: Drawable? = null, button: CharSequence? = null,
                   buttonListener: View.OnClickListener? = null){
        (requireActivity() as AppActivity).setToolbar(title, subtitle, drawable, button, buttonListener)
    }

    fun setToolbarVisibility(isVisible: Boolean){
        (requireActivity() as AppActivity).setToolbarVisibility(isVisible)
    }

    fun navigate(directions: NavDirections, navOptionsBuilder: NavOptions.Builder? = null) {
        navigate(directions.actionId, directions.arguments, false, navOptionsBuilder)
    }

    fun navigate(@IdRes resId: Int, navOptionsBuilder: NavOptions.Builder? = null) {
        navigate(resId, null, false, navOptionsBuilder)
    }

    @SuppressLint("RestrictedApi")
    fun navigate(@IdRes resId: Int, args: Bundle?, isLogout: Boolean = false, optionsBuilder: NavOptions.Builder? = null) {

        val navOptionsBuilder = optionsBuilder ?: NavOptions.Builder()
        val animate = true

        if (animate) {
            navOptionsBuilder.setEnterAnim(R.anim.nav_enter_anim)
                .setExitAnim(R.anim.nav_exit_anim)
                .setPopEnterAnim(R.anim.nav_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_pop_exit_anim)
        }

        if (isLogout) {
            navOptionsBuilder.setPopUpTo(findNavController().backStack.first.destination.id, true)
            navOptionsBuilder.setLaunchSingleTop(true) // this is only needed on lateral movements
        } else if (resId == R.id.action_global_loginFragment) {
            // Allow only one Login screen
            navOptionsBuilder.setLaunchSingleTop(true)
        }else if (resId == R.id.action_global_addWalletFragment){
            // Allow a single onboarding path
            navOptionsBuilder.setPopUpTo(R.id.addWalletFragment, true)
        }

        try{
            // Simple fix for https://issuetracker.google.com/issues/118975714
            findNavController().navigate(resId, args, navOptionsBuilder.build())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun openOverview(){
        val intent = Intent(requireContext(), TabbedMainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun popBackStack(){
        findNavController().popBackStack()
    }

    override fun interactionRequest(hw: HWWallet?) {
        throw Exception("Not yet implemented")
    }

    override fun pinMatrixRequest(hw: HWWallet?): String {
        throw Exception("Not yet implemented")
    }

    override fun passphraseRequest(hw: HWWallet?): String {
        throw Exception("Not yet implemented")
    }
}