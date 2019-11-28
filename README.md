# TDMWF description #
This is web interface for HTTP downloader by some cool guy.


# How it's work #

The web-interface are necessary to show information about downloads and provide control interface in real-time.

Downloader observer watching on downloads.
When downloads was changed observer make a JSON-information object and send one to client.

JSON-object description:

```javascript
{
    "filename": "The file name",
    "fileSize": 128,
    "threads":
    {
        "1": {
                "start": 0,
                "endByte": 64,
                "progress": 50.123,
                "isFinished": false
        },
        "2": {
                "start": 65,
                "endByte": 128,
                "progress": 55.12,
                "isFinished": false
        },
    },
    "progress": 50.12345,
    "id": 1,
    "downloaded": 0,
    "status": 0
}
```

# Control description #

For control downloads the client send an object contains executable name, data or ID of downloads.
Next java cmd.manager are make decision what kind of command will be call.

Command | Data
------------ | -------------
Add | Data contains file URL
Pause | ID contains download identity for pause
Resume | ID contains download identity for
