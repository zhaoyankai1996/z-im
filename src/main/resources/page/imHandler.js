var IMHandler = function () {

    this.onopen = function (event, ws) {
        // ws.send('hello 连上了哦')
        //document.getElementById('contentId').innerHTML += 'hello 连上了哦<br>';
    }

    /**
     * 收到服务器发来的消息
     * @param {*} event
     * @param {*} ws
     */
    this.onmessage = function (event, ws) {
        var data = event.data;
        console.log(JSON.parse(data))
        var msgBody = JSON.parse(data);
        if(msgBody.code == -1){
            return -1;
        }
        var ico, imgcls, spancls, ncikcls;
        var content = document.getElementsByTagName('ul')[0];
        if (myname == msgBody.from) {
            ico = "image/t2.jpg";
            imgcls = "imgright";
            spancls = "spanright";
            nickcls = "nickright";
        } else {
            ico = "image/t1.jpg";
            imgcls = "imgleft";
            spancls = "spanleft";
            nickcls = "nickleft"
        }
        //myself=!myself;
        if (msgBody.from == "admin") {
            content.innerHTML += '<li><div class="sysinfo">' + msgBody.msg + '</div></li>';
        } else {
            content.innerHTML += '<li><img src="' + ico + '" class="' + imgcls + '"><span class="' + nickcls + '">' + msgBody.from + '</span><span class="' + spancls + '">' + msgBody.msg + '</span></li>';
        }

        //document.getElementById('contentId').innerHTML += data + '<br>'
        content.scrollTop = content.scrollHeight;
    }

    this.onclose = function (e, ws) {
        // error(e, ws)
    }

    this.onerror = function (e, ws) {
        // error(e, ws)
    }

    /**
     * 发送心跳，本框架会自动定时调用该方法，请在该方法中发送心跳
     * @param {*} ws
     */
    this.ping = function (ws) {
        // log("发心跳了")
        ws.send('心跳内容')
    }
}
