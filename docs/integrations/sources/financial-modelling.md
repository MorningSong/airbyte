# Financial Modelling
FMP provides financial data.
Using this connector we can extract data from various endpoints like Stocks list, ETFs list , Exchange Symbols and Historical MarketCap etc
Docs : https://site.financialmodelingprep.com/developer/docs

## Configuration

| Input | Type | Description | Default Value |
|-------|------|-------------|---------------|
| `api_key` | `string` | API Key.  |  |
| `exchange` | `string` | Exchange. The stock exchange : AMEX, AMS, AQS, ASX, ATH, BER, BME, BRU, BSE, BUD, BUE, BVC, CAI, CBOE, CNQ, CPH, DFM, DOH, DUS, DXE, EGX, EURONEXT, HAM, HEL, HKSE, ICE, IOB, IST, JKT, JNB, JPX, KLS, KOE, KSC, KUW, LSE, MCX, MEX, MIL, MUN, NASDAQ, NEO, NSE, NYSE, NZE, OEM, OQX, OSL, OTC, PNK, PRA, RIS, SAO, SAU, SES, SET, SGO, SHH, SHZ, SIX, STO, STU, TAI, TLV, TSX, TSXV, TWO, VIE, VSE, WSE, XETRA | NASDAQ |
| `marketcapmorethan` | `string` | marketCapMoreThan. Used in screener to filter out stocks with a market cap more than the give marketcap |  |
| `marketcaplowerthan` | `string` | marketCapLowerThan. Used in screener to filter out stocks with a market cap lower than the give marketcap |  |
| `start_date` | `string` | Start Date |  |
| `time_frame` | `string` | Time Frame. For example 1min, 5min, 15min, 30min, 1hour, 4hour | 1hour |

## Streams
| Stream Name | Primary Key | Pagination | Supports Full Sync | Supports Incremental |
|-------------|-------------|------------|---------------------|----------------------|
| Stocks List |  | No pagination | ✅ |  ❌  |
| ETFs List |  | No pagination | ✅ |  ❌  |
| Tradable Search  |  | No pagination | ✅ |  ❌  |
| CIK List |  | No pagination | ✅ |  ❌  |
| Euronext Symbols |  | No pagination | ✅ |  ❌  |
| Exchange Symbols |  | No pagination | ✅ |  ❌  |
| Available Indexes |  | No pagination | ✅ |  ❌  |
| Company Profile |  | No pagination | ✅ |  ❌  |
| Screener (Stock) |  | No pagination | ✅ |  ❌  |
| Historical Market Cap |  | No pagination | ✅ |  ✅  |
| Delisted Companies |  | No pagination | ✅ |  ❌  |
| Exchange Prices |  | No pagination | ✅ |  ❌  |
| All RealTime Full Stock Prices |  | No pagination | ✅ |  ❌  |
| ALL FX Prices |  | No pagination | ✅ |  ❌  |
| Stock Historical Price |  | No pagination | ✅ |  ✅  |
| Forex List |  | No pagination | ✅ |  ❌  |
| Cryptocurrencies List |  | No pagination | ✅ |  ❌  |

## Changelog

<details>
  <summary>Expand to review</summary>

| Version          | Date              | Pull Request | Subject        |
|------------------|-------------------|--------------|----------------|
| 0.0.29 | 2025-07-26 | [63978](https://github.com/airbytehq/airbyte/pull/63978) | Update dependencies |
| 0.0.28 | 2025-07-19 | [63558](https://github.com/airbytehq/airbyte/pull/63558) | Update dependencies |
| 0.0.27 | 2025-07-12 | [62981](https://github.com/airbytehq/airbyte/pull/62981) | Update dependencies |
| 0.0.26 | 2025-07-05 | [62785](https://github.com/airbytehq/airbyte/pull/62785) | Update dependencies |
| 0.0.25 | 2025-06-28 | [62383](https://github.com/airbytehq/airbyte/pull/62383) | Update dependencies |
| 0.0.24 | 2025-06-21 | [61937](https://github.com/airbytehq/airbyte/pull/61937) | Update dependencies |
| 0.0.23 | 2025-06-14 | [61188](https://github.com/airbytehq/airbyte/pull/61188) | Update dependencies |
| 0.0.22 | 2025-05-24 | [60367](https://github.com/airbytehq/airbyte/pull/60367) | Update dependencies |
| 0.0.21 | 2025-05-10 | [59988](https://github.com/airbytehq/airbyte/pull/59988) | Update dependencies |
| 0.0.20 | 2025-05-03 | [58852](https://github.com/airbytehq/airbyte/pull/58852) | Update dependencies |
| 0.0.19 | 2025-04-19 | [58332](https://github.com/airbytehq/airbyte/pull/58332) | Update dependencies |
| 0.0.18 | 2025-04-12 | [57822](https://github.com/airbytehq/airbyte/pull/57822) | Update dependencies |
| 0.0.17 | 2025-04-05 | [57232](https://github.com/airbytehq/airbyte/pull/57232) | Update dependencies |
| 0.0.16 | 2025-03-29 | [56517](https://github.com/airbytehq/airbyte/pull/56517) | Update dependencies |
| 0.0.15 | 2025-03-22 | [55983](https://github.com/airbytehq/airbyte/pull/55983) | Update dependencies |
| 0.0.14 | 2025-03-08 | [54984](https://github.com/airbytehq/airbyte/pull/54984) | Update dependencies |
| 0.0.13 | 2025-02-22 | [54436](https://github.com/airbytehq/airbyte/pull/54436) | Update dependencies |
| 0.0.12 | 2025-02-15 | [53730](https://github.com/airbytehq/airbyte/pull/53730) | Update dependencies |
| 0.0.11 | 2025-02-08 | [53325](https://github.com/airbytehq/airbyte/pull/53325) | Update dependencies |
| 0.0.10 | 2025-02-01 | [52796](https://github.com/airbytehq/airbyte/pull/52796) | Update dependencies |
| 0.0.9 | 2025-01-25 | [52305](https://github.com/airbytehq/airbyte/pull/52305) | Update dependencies |
| 0.0.8 | 2025-01-18 | [51634](https://github.com/airbytehq/airbyte/pull/51634) | Update dependencies |
| 0.0.7 | 2025-01-11 | [51064](https://github.com/airbytehq/airbyte/pull/51064) | Update dependencies |
| 0.0.6 | 2024-12-28 | [50524](https://github.com/airbytehq/airbyte/pull/50524) | Update dependencies |
| 0.0.5 | 2024-12-21 | [50060](https://github.com/airbytehq/airbyte/pull/50060) | Update dependencies |
| 0.0.4 | 2024-12-14 | [49516](https://github.com/airbytehq/airbyte/pull/49516) | Update dependencies |
| 0.0.3 | 2024-12-12 | [49157](https://github.com/airbytehq/airbyte/pull/49157) | Update dependencies |
| 0.0.2 | 2024-11-04 | [48299](https://github.com/airbytehq/airbyte/pull/48299) | Update dependencies |
| 0.0.1 | 2024-10-22 | | Initial release by [@ombhardwajj](https://github.com/ombhardwajj) via Connector Builder |

</details>
