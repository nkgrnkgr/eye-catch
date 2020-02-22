# eye-catch

an ApiServer for eye-catch-data

### Requests

#### GET

```bash
curl -X GET http:localhost:8080/api/eyeCatch
```

```json
{
    "eyeCatch": {
        "card": "summary_large_image",
        "creator": "@nkgrnkgr",
        "description": "Nokogiri - Portfolio",
        "imageUrl": "https://raw.githubusercontent.com/nkgrnkgr/portfolio/master/src/images/top.png",
        "site": "@nkgrnkgr",
        "themeColor": "themeColor",
        "title": "Nokogiri - Portfolio",
        "url": "https://nkgr.app"
    },
    "status": 200
}
```

#### POST

```bash
curl -X POST -H "Content-type: application/json" http://localhost:8080/api/eyeCatch -d '{"url":"https://www.nkgr.app"}'
```

```json
{
  "status": 200,
  "eyeCatch": {
    "themeColor": "#ff3860",
    "card": "summary_large_image",
    "site": "@nkgrnkgr",
    "creator": null,
    "title": "Nokogiri - Portfolio",
    "imageUrl": "https://raw.githubusercontent.com/nkgrnkgr/portfolio/master/src/images/top.png",
    "description": "Nokogiri - Portfolio",
    "url": "https://nkgr.app"
  }
}
```

#### Scraping NotFoundURL

```json
{
  "timestamp": "2020-02-22T12:39:49.593+0000",
  "path": "/api/eyeCatch",
  "status": 500,
  "error": "Internal Server Error",
  "message": "www.nkgr.ap",
  "requestId": "f0fd039e"
}
```

### lint

```bash
./gradlew ktlint
```

### format

```bash
./gradlew ktlintFormat
