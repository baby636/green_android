# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [3.6.4] - 2021-07-23
### Added
- Adds a PIN pad for 2FA codes

### Changed
- Improves Wallet Settings UI
- Drops support for bitcoinj and SPV on Multisig Shield wallets

### Fixed
- Fixes crashes with fingerprint login authentication
- Shows PGP and Watch-Only settings only on Multisig Shield wallets

## [3.6.3] - 2021-07-12
### Added
- Support for creating and restoring Singlesig wallets on Bitcoin Testnet
- Enhanced support for Blockstream Jade

### Changed
- Improves address validation on hardware wallets
- Preloads icons of Liquid Assets
- Improves handling of disconnection and reconnection
- Adds a warning when opening Help Center articles while using Tor
- Updates GDK to 0.0.43

### Fixed
- Ledger NanoX devices device identification
- Bluetooth device discovery
- Decimal and fiat amount request on receive
- Crash on Watch-Only wallets
- Crash when resuming the application after prolonged period of inactivity

## [3.6.0] - 2021-05-31
### Added
- Show the wallet name in the main view
- Cache liquid asset registry in addition to GDK caching
- Display the account type in the account cards
- Improve tools for testing
- Revamp receive view with new UI and button to verify addresses on hardware wallets

### Changed
- Generate 12 words recovery phrases by default
- Update Android and GDK dependencies

### Fixed
- Fix error handling when restoring or creating wallets
- Fix migration from v2 android screenlock logins
