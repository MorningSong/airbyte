# Reddit

## Overview

The Reddit source supports _Full Refresh_ as well as _Incremental_ syncs.

_Full Refresh_ sync means every time a sync is run, Airbyte will copy all rows in the tables and columns you set up for replication into the destination in a new table.
_Incremental_ sync means only changed resources are copied from Reddit. For the first run, it will be a Full Refresh sync.


## Steps for getting `api_key`

You can make a POST request from Postman to exchange your Reddit username and password for an `api_key` authorized to make requests.

First make an app to get the client ID and secret for authentication:

1. Go to Reddit's App Preferences Page:
- Visit `https://www.reddit.com/prefs/apps`, select `create another app` and input an app name. Select the `script` option and set the redirect URI as `https://oauth.pstmn.io/v1/callback`.

2. Copy Your App Credentials:
 - After creating the app, you will see the Client ID (below your app name) and Client Secret (labeled as "secret").
 - Client ID: Copy this value as it will be your Authorization Username in Postman.
 - Client Secret: Copy this value as it will be your Authorization Password in Postman.

3. Visit Postman via web or app and make a new request with following guidelines:
 - Request - POST `https://www.reddit.com/api/v1/access_token`
 - Authorization - Basic Auth -`username: <YOUR_USERNAME>`, `password: <YOUR_PASSWORD>`
 - Body - x-www-form-urlencoded - `grant_type: password, username: YOUR_REDDIT_USERNAME, password: YOUR_REDDIT_PASSWORD`

Hit send to receive `api_key` in the response under `access_token`

## Records and rate limiting

- The Reddit API has [rate limiting of 100 queries per minute (QPM) per OAuth client ID](https://support.reddithelp.com/hc/en-us/articles/16160319875092-Reddit-Data-API-Wiki). It is handled with an exponential backoff strategy, with maximum 3 retries.
- If the `api_key` expires, a new access token will need to be generated through Postman.
- The Reddit API has a hard limit of fetching 1000 records per single stream call with subsequent pagination.

## Configuration

| Input | Type | Description | Default Value |
|-------|------|-------------|---------------|
| `api_key` | `string` | API Key.  |  |
| `query` | `string` | Query. Specifies the query for searching in reddits and subreddits | airbyte |
| `include_over_18` | `boolean` | Include over 18 flag. Includes mature content | false |
| `exact` | `boolean` | Exact. Specifies exact keyword and reduces distractions |  |
| `limit` | `number` | Limit. Max records per page limit | 1000 |
| `subreddits` | `array` | Subreddits. Subreddits for exploration | [r/funny, r/AskReddit] |
| `start_date` | `string` | Start date.  |  |

## Streams
| Stream Name | Primary Key | Pagination | Supports Full Sync | Supports Incremental |
|-------------|-------------|------------|---------------------|----------------------|
| self | name | No pagination | ✅ |  ❌  |
| search |  | DefaultPaginator | ✅ |  ❌  |
| subreddit_search |  | DefaultPaginator | ✅ |  ❌  |
| message_inbox |  | DefaultPaginator | ✅ |  ❌  |
| subreddit_popular |  | DefaultPaginator | ✅ |  ❌  |
| subreddit_explore |  | DefaultPaginator | ✅ |  ✅  |


## Changelog

<details>
  <summary>Expand to review</summary>

| Version          | Date       |Pull Request | Subject        |
|------------------|------------|--------------|----------------|
| 0.0.30 | 2025-07-19 | [63628](https://github.com/airbytehq/airbyte/pull/63628) | Update dependencies |
| 0.0.29 | 2025-07-12 | [63054](https://github.com/airbytehq/airbyte/pull/63054) | Update dependencies |
| 0.0.28 | 2025-07-05 | [62697](https://github.com/airbytehq/airbyte/pull/62697) | Update dependencies |
| 0.0.27 | 2025-06-28 | [62217](https://github.com/airbytehq/airbyte/pull/62217) | Update dependencies |
| 0.0.26 | 2025-06-21 | [61787](https://github.com/airbytehq/airbyte/pull/61787) | Update dependencies |
| 0.0.25 | 2025-06-14 | [60557](https://github.com/airbytehq/airbyte/pull/60557) | Update dependencies |
| 0.0.24 | 2025-05-10 | [60162](https://github.com/airbytehq/airbyte/pull/60162) | Update dependencies |
| 0.0.23 | 2025-05-03 | [59473](https://github.com/airbytehq/airbyte/pull/59473) | Update dependencies |
| 0.0.22 | 2025-04-27 | [59106](https://github.com/airbytehq/airbyte/pull/59106) | Update dependencies |
| 0.0.21 | 2025-04-19 | [58458](https://github.com/airbytehq/airbyte/pull/58458) | Update dependencies |
| 0.0.20 | 2025-04-12 | [57923](https://github.com/airbytehq/airbyte/pull/57923) | Update dependencies |
| 0.0.19 | 2025-04-05 | [57298](https://github.com/airbytehq/airbyte/pull/57298) | Update dependencies |
| 0.0.18 | 2025-03-29 | [56770](https://github.com/airbytehq/airbyte/pull/56770) | Update dependencies |
| 0.0.17 | 2025-03-22 | [56163](https://github.com/airbytehq/airbyte/pull/56163) | Update dependencies |
| 0.0.16 | 2025-03-08 | [55061](https://github.com/airbytehq/airbyte/pull/55061) | Update dependencies |
| 0.0.15 | 2025-02-23 | [54586](https://github.com/airbytehq/airbyte/pull/54586) | Update dependencies |
| 0.0.14 | 2025-02-15 | [53957](https://github.com/airbytehq/airbyte/pull/53957) | Update dependencies |
| 0.0.13 | 2025-02-08 | [53455](https://github.com/airbytehq/airbyte/pull/53455) | Update dependencies |
| 0.0.12 | 2025-02-01 | [53004](https://github.com/airbytehq/airbyte/pull/53004) | Update dependencies |
| 0.0.11 | 2025-01-25 | [52494](https://github.com/airbytehq/airbyte/pull/52494) | Update dependencies |
| 0.0.10 | 2025-01-18 | [51854](https://github.com/airbytehq/airbyte/pull/51854) | Update dependencies |
| 0.0.9 | 2025-01-11 | [51376](https://github.com/airbytehq/airbyte/pull/51376) | Update dependencies |
| 0.0.8 | 2024-12-28 | [50683](https://github.com/airbytehq/airbyte/pull/50683) | Update dependencies |
| 0.0.7 | 2024-12-21 | [50232](https://github.com/airbytehq/airbyte/pull/50232) | Update dependencies |
| 0.0.6 | 2024-12-14 | [49697](https://github.com/airbytehq/airbyte/pull/49697) | Update dependencies |
| 0.0.5 | 2024-12-12 | [49368](https://github.com/airbytehq/airbyte/pull/49368) | Update dependencies |
| 0.0.4 | 2024-12-11 | [49104](https://github.com/airbytehq/airbyte/pull/49104) | Starting with this version, the Docker image is now rootless. Please note that this and future versions will not be compatible with Airbyte versions earlier than 0.64 |
| 0.0.3 | 2024-10-29 | [47827](https://github.com/airbytehq/airbyte/pull/47827) | Update dependencies |
| 0.0.2 | 2024-10-28 | [47542](https://github.com/airbytehq/airbyte/pull/47542) | Update dependencies |
| 0.0.1 | 2024-08-23 | [44579](https://github.com/airbytehq/airbyte/pull/44579) | Initial release by [btkcodedev](https://github.com/btkcodedev) via Connector Builder |

</details>
