/**
 * phantomJs 脚本
 */
var page = require('webpage').create(),
    system = require('system'),
    address, output, size;
phantom.outputEncoding = "gb2312"
if (system.args.length < 3 || system.args.length > 5) {
    phantom.exit(1);
} else {
    address = system.args[1];
    output = system.args[2];
    console.log(address)
    //定义宽高
    page.viewportSize = {
        width: 1366,
        height: 600
    };
    page.settings.userAgent = 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36';
    page.settings.cookie = "ci=62; abt=1500792525.0%7CADE; rvct=62; _lxsdk_cuid=15d6e35adb0c8-0bfd8f185fadc3-474a0521-13c680-15d6e35adb0c8; uuid=6a4564d31b9935664045.1500792525.0.0.0; oc=ysQNElvI-JId2ulOE4zGH02McnpQC_Ct0V5sG4vw-6XheJoi4Elx-G5GmfjuhJ9USrlcTcYhJ-ovwPaz-EJ8oj3o2pFWMv-gvFNGIMwM7sZ1WlbaOGryWPOh7HqC6CQvlRB1Aa5rzj-jnij7qBgki1eRr6f02_Wv7tR7SNS0qi8; w_uuid=Z5qMrYF7KAwNdHjWtk2051Oowp6HaipFfsoVeqmFDZVD7lGzL7_THCfUmHNNcHQU; w_utmz=\"utm_campaign=(direct)&utm_source=(direct)&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; _hc.v=ff54b949-d406-1c19-2719-6c6d9f8f3a45.1519957077; waddrname=\"%E6%B9%96%E6%BB%A8%E5%8D%97%E8%B7%AF\"; w_geoid=wsk520ch4dhu; w_cid=350203; w_cpy=simingqu; w_cpy_cn=\"%E6%80%9D%E6%98%8E%E5%8C%BA\"; w_ah=\"24.482356980443,118.12648694962263,%E6%B9%96%E6%BB%A8%E5%8D%97%E8%B7%AF\"; JSESSIONID=fd68ttzhzfw1i92n4s525vos; __mta=88334116.1505634547498.1524218547922.1524218623234.27; _lxsdk_s=%7C%7C0"
    page.settings.cacheControl = "max-age=0"
    page.settings.host="waimai.meituan.com"
    page.settings.upgradeInsecureRequests="1"
    page.settings.connection="keep-alive"
    page.open(address, function(status) {
        console.log("打开页面");
        console.log(status);
        if (status != "success") {
            return
        }
        page.evaluate(function() {
            var y = 0;
            var step = 100;
            window.scroll(0, 0);

            function f() {
                if (y < document.body.scrollHeight) {
                    y += step;
                    window.scroll(0, y);
                    setTimeout(f, 50);
                } else {
                    setTimeout(function() {
                        window.scroll(0, 0);
                        document.title += "scroll-done";
                    }, 1000)
                }
            }
            setTimeout(f, 1000);
        });

        function monitor() {
            var finished = page.evaluate(function() {
                return document.title.indexOf("scroll-done") > -1
            })
            if (finished) {
                window.setTimeout(function() {
                    page.render(output);
                    page.close();
                    console.log('渲染成功...');
                    phantom.exit(0);
                }, 2000);
            } else {
                setTimeout(monitor, 1000);
            }
        }
        setTimeout(monitor, 1000);
    })
}