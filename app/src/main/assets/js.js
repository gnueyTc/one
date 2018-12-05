(function($) {
	function detect(ua) {

		var MQQBrowser = ua.match(/MQQBrowser\/(\d+\.\d+)/i),
		WeChat = ua.match(/MicroMessenger\/((\d+)\.(\d+))\.(\d+)/),
		MacOS = ua.match(/Mac\sOS\sX\s(\d+\.\d+)/),
		WinOS = ua.match(/Windows(\s+\w+)?\s+?(\d+\.\d+)/),
		MiuiBrowser = ua.match(/MiuiBrowser\/(\d+\.\d+)/i),
		UC = ua.match(/UCBrowser\/(\d+\.\d+(\.\d+\.\d+)?)/) || ua.match(/\sUC\s/),
		IEMobile = ua.match(/IEMobile(\/|\s+)(\d+\.\d+)/),
		ipod = ua.match(/(ipod\sOS)\s([\d_]+)/);


		// 閹碘晛鐫峣e閸掋倖鏌?
		if (window.ActiveXObject) {
			var vie = 6;
			(window.XMLHttpRequest || (ua.indexOf('MSIE 7.0') > -1)) && (vie = 7);
			(window.XDomainRequest || (ua.indexOf('Trident/4.0') > -1)) && (vie = 8);
			(ua.indexOf('Trident/5.0') > -1) && (vie = 9);
			(ua.indexOf('Trident/6.0') > -1) && (vie = 10);
			this.browser.ie = true, this.browser.version = vie;
		}

		if (ipod) os.ios = os.ipod = true, os.version = ipod[2].replace(/_/g, '.');
		//windows 缁崵绮?
		if (WinOS) this.os.windows = true, this.os.version = WinOS[2];
		//Mac缁崵绮?
		if (MacOS) this.os.Mac = true, this.os.version = MacOS[1];
		//娑旀€璦d
		if (ua.indexOf("lepad_hls") > 0) this.os.LePad = true;

		//鐞涖儱鍘栨稉鈧禍娑樻禇閸愬懍瀵屽ù浣烘畱閹靛婧€濞村繗顫嶉崳?
		//閹靛婧€QQ濞村繗顫嶉崳?
		if (MQQBrowser) this.browser.MQQ = true, this.browser.version = MQQBrowser[1];
		//瀵邦喕淇?
		if (WeChat) this.browser.WeChat = true, this.browser.version = WeChat[1];
		//MIUI閼奉亜鐢ù蹇氼潔閸?
		if (MiuiBrowser) this.browser.MIUI = true, this.browser.version = MiuiBrowser[1];
		//UC濞村繗顫嶉崳?
		if (UC) this.browser.UC = true, this.browser.version = UC[1] || NaN;
		//IEMobile
		if (IEMobile) this.browser.IEMobile = true, this.browser.version = IEMobile[2];

		if (this.os.windows) {
			if (typeof navigator.platform != "undefined" && navigator.platform.toLowerCase() == "win64") {
				this.os.win64 = true;
			} else {
				this.os.win64 = false;
			}
		}

		//瑜版挸澧犵化鑽ょ埠閺勵垰鎯侀弨顖涘瘮鐟欙箑鐫嗙憴锔芥嚋
		this.os.hasTouch = ('ontouchstart' in window);
		if(ua.match(/OneApp\/\d/)){
			this.browser.oneapp = true;
		} else {
			this.browser.oneapp = false;
		}
        this.browser.Weibo = / Weibo/.test(ua);
        this.browser.QQ = / QQ/.test(ua);
	}
	detect.call($, navigator.userAgent);
})(Zepto);

//jsbridge setting

Zepto(function(){
    if(!window.ONEGLOBAL) window.ONEGLOBAL = {func:{}};
	//jsbridge 閸掓繂顫愰崠?
	ONEGLOBAL.setupWebViewJavascriptBridge = function(callback){};
	if(Zepto.browser.oneapp && Zepto.os.ios){
		console.log('webview setup bridge for iOS');
		ONEGLOBAL.setupWebViewJavascriptBridge = function (callback) {
			if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
			if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
			window.WVJBCallbacks = [callback];
			var WVJBIframe = document.createElement('iframe');
			WVJBIframe.style.display = 'none';
			WVJBIframe.src = 'https://__bridge_loaded__';
			document.documentElement.appendChild(WVJBIframe);
			setTimeout(function() { document.documentElement.removeChild(WVJBIframe); }, 0);
		};
	} else if(Zepto.browser.oneapp && Zepto.os.android){
		console.log('webview setup bridge for android');
		ONEGLOBAL.setupWebViewJavascriptBridge = function (callback) {
			if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
			if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
			window.WVJBCallbacks = [callback];
			var WVJBIframe = document.createElement('iframe');
			WVJBIframe.style.display = 'none';
			WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
			document.documentElement.appendChild(WVJBIframe);
			setTimeout(function() { document.documentElement.removeChild(WVJBIframe); }, 0);
		};
	} else {
		console.log('webview setup bridge for m缁?');
		ONEGLOBAL.setupWebViewJavascriptBridge = function(callback){
			var bridge = {
				callHandler: function(name, params, cb){
					console.log("CALL "+name);
					console.log(params);
                    //cb({});
				},
				registerHandler: function(name, cb){
					console.log("REGISTER "+name);
				}
			};
			callback(bridge);
		};
	}
	 ONEGLOBAL._makeStatistics = function(data){
        var params = {
            platform  : data.stat.platform  || '',
            version   : data.stat.version   || '',
            user_id   : data.stat.user_id   || '',
            uuid      : data.stat.uuid      || '',
            cache     : ONEGLOBAL.cache == "true"  ? 1 : 0,
            source    : data.stat.source    || '',
            source_id : data.stat.source_id || '',
            type      : data.stat.type      || '',//essay,music
            id        : data.stat.id        || '',//essay->id, music->id
            page      : 'detail',
            channel   : data.stat.channel   || '',//yingyongbao, mi
        };
        //var url = "http://analytical.wufazhuce.com:81/?t="+Date.now();
        if(ONEGLOBAL.staturl) {
            var url = ONEGLOBAL.staturl += "?t=" + Date.now();
            Zepto.each(params, function(k,v){ url += "&" + k + "=" + v; });
            setTimeout(function(){
                Zepto('body').append('<img class="one-image-exclude" src="'+url+'" style="display:none;visibility:hidden;height:1px;width:1px;" width="1px" height="1px">');
            },300);
        }

    }
    ONEGLOBAL._makeStatisticsForWap = function (){
            var pathArray = window.location.pathname.split( '/' );
            pathArray.push("");pathArray.push("");pathArray.push("");
            var isDetailPage = /^\d+$/.test(pathArray[2]); 
            if(!isDetailPage) return;
            var channel = "wap";
            if(Zepto.browser.QQ){
                channel = "QQ";
            } else if(Zepto.browser.Weibo){
                channel = "Weibo";
            } else if(Zepto.browser.WeChat){
                if(/from=singlemessage/.test(window.location.search)){
                   channel = "singlemessage"; 
                } else if (/from=timeline/.test(window.location.search)){
                    channel = "timeline"
                } else {
                    channel = "Wechat";
                }
            }
            ONEGLOBAL._makeStatistics({stat:{
                platform  : 'M',
                version   : '4.1.0',
                user_id   : '',
                uuid      : '',
                cache     :  0,
                source    : encodeURIComponent(document.referrer),
                source_id : '',
                type      : pathArray[1],
                id        : pathArray[2],
                page      : 'detail',
                channel   : channel,
            }});
    }
    ONEGLOBAL._isInViewport = function(element, fullyInView) {
        var pageTop = Zepto(window).scrollTop();
        var pageBottom = pageTop + Zepto(window).height();
        var elementTop = Zepto(element).offset().top;
        var elementBottom = elementTop + Zepto(element).height();
        if (fullyInView === true) {
            return ((pageTop < elementTop) && (pageBottom > elementBottom));
        } else {
            return ((elementTop <= pageBottom) && (elementBottom >= pageTop));
        }
    };
});

/*!
 * Swipe 2.2.8
 *
 * Brad Birdsall
 * Copyright 2013, MIT License
 *
*/
!function(a,b){"function"==typeof define&&define.amd?define([],function(){return a.Swipe=b(),a.Swipe}):"object"==typeof module&&module.exports?module.exports=b():a.Swipe=b()}(this,function(){function Swipe(c,d){"use strict";function e(){I.addEventListener?(N.removeEventListener("touchstart",R,!1),N.removeEventListener("mousedown",R,!1),N.removeEventListener("webkitTransitionEnd",R,!1),N.removeEventListener("msTransitionEnd",R,!1),N.removeEventListener("oTransitionEnd",R,!1),N.removeEventListener("otransitionend",R,!1),N.removeEventListener("transitionend",R,!1),a.removeEventListener("resize",R,!1)):a.onresize=null}function f(){I.addEventListener?(I.touch&&N.addEventListener("touchstart",R,!1),d.draggable&&N.addEventListener("mousedown",R,!1),I.transitions&&(N.addEventListener("webkitTransitionEnd",R,!1),N.addEventListener("msTransitionEnd",R,!1),N.addEventListener("oTransitionEnd",R,!1),N.addEventListener("otransitionend",R,!1),N.addEventListener("transitionend",R,!1)),a.addEventListener("resize",R,!1)):a.onresize=Q}function g(a){var b=a.cloneNode(!0);N.appendChild(b),b.setAttribute("data-cloned",!0),b.removeAttribute("id")}function h(){J=N.children,M=J.length;for(var a=0;a<J.length;a++)J[a].getAttribute("data-cloned")&&M--;J.length<2&&(d.continuous=!1),I.transitions&&d.continuous&&J.length<3&&(g(J[0]),g(J[1]),J=N.children),K=new Array(J.length),L=c.getBoundingClientRect().width||c.offsetWidth,N.style.width=J.length*L*2+"px";for(var b=J.length;b--;){var h=J[b];h.style.width=L+"px",h.setAttribute("data-index",b),I.transitions&&(h.style.left=b*-L+"px",p(b,O>b?-L:O<b?L:0,0))}d.continuous&&I.transitions&&(p(m(O-1),-L,0),p(m(O+1),L,0)),I.transitions||(N.style.left=O*-L+"px"),c.style.visibility="visible",e(),f()}function i(){E||(d.continuous?o(O-1):O&&o(O-1))}function j(){E||(d.continuous?o(O+1):O<J.length-1&&o(O+1))}function k(a,b,c){d.callback&&d.callback(a,b,c)}function l(a,b){d.transitionEnd&&d.transitionEnd(a,b)}function m(a){return(J.length+a%J.length)%J.length}function n(){var a=O;return a>=M&&(a-=M),a}function o(a,b){if(a="number"!=typeof a?parseInt(a,10):a,O!==a){if(I.transitions){var c=Math.abs(O-a)/(O-a);if(d.continuous){var e=c;c=-K[m(a)]/L,c!==e&&(a=-c*J.length+a)}for(var f=Math.abs(O-a)-1;f--;)p(m((a>O?a:O)-f-1),L*c,0);a=m(a),p(O,L*c,b||P),p(a,0,b||P),d.continuous&&p(m(a-c),-(L*c),0)}else a=m(a),r(O*-L,a*-L,b||P);O=a,G(function(){k(n(),J[O],c)})}}function p(a,b,c){q(a,b,c),K[a]=b}function q(a,b,c){var d=J[a],e=d&&d.style;e&&(e.webkitTransitionDuration=e.MozTransitionDuration=e.msTransitionDuration=e.OTransitionDuration=e.transitionDuration=c+"ms",e.webkitTransform="translate("+b+"px,0)translateZ(0)",e.msTransform=e.MozTransform=e.OTransform="translateX("+b+"px)")}function r(a,b,c){if(!c)return void(N.style.left=b+"px");var e=+new Date,f=setInterval(function(){var g=+new Date-e;return g>c?(N.style.left=b+"px",(D||d.autoRestart)&&u(),l(n(),J[O]),void clearInterval(f)):void(N.style.left=(b-a)*(Math.floor(g/c*100)/100)+a+"px")},4)}function s(){A=setTimeout(j,D)}function t(){D=0,clearTimeout(A)}function u(){t(),D=d.auto||0,s()}function v(){t(),E=!0}function w(){E=!1,u()}function x(a){return/^mouse/.test(a.type)}function y(){t(),c.style.visibility="",N.style.width="",N.style.left="";for(var a=J.length;a--;){I.transitions&&q(a,0,0);var b=J[a];if(b.getAttribute("data-cloned")){var d=b.parentElement;d.removeChild(b)}b.style.width="",b.style.left="",b.style.webkitTransitionDuration=b.style.MozTransitionDuration=b.style.msTransitionDuration=b.style.OTransitionDuration=b.style.transitionDuration="",b.style.webkitTransform=b.style.msTransform=b.style.MozTransform=b.style.OTransform=""}e(),Q.cancel()}d=d||{};var z,A,B={},C={},D=d.auto||0,E=!1,F=function(){},G=function(a){setTimeout(a||F,0)},H=function(a,b){function c(){e&&clearTimeout(e)}function d(){var d=this,f=arguments;c(),e=setTimeout(function(){e=null,a.apply(d,f)},b)}b=b||100;var e=null;return d.cancel=c,d},I={addEventListener:!!a.addEventListener,touch:"ontouchstart"in a||a.DocumentTouch&&b instanceof DocumentTouch,transitions:function(a){var b=["transitionProperty","WebkitTransition","MozTransition","OTransition","msTransition"];for(var c in b)if(void 0!==a.style[b[c]])return!0;return!1}(b.createElement("swipe"))};if(c){var J,K,L,M,N=c.children[0],O=parseInt(d.startSlide,10)||0,P=d.speed||300;d.continuous=void 0===d.continuous||d.continuous,d.autoRestart=void 0!==d.autoRestart&&d.autoRestart;var Q=H(h),R={handleEvent:function(a){if(!E){switch(a.type){case"mousedown":case"touchstart":this.start(a);break;case"mousemove":case"touchmove":this.move(a);break;case"mouseup":case"mouseleave":case"touchend":this.end(a);break;case"webkitTransitionEnd":case"msTransitionEnd":case"oTransitionEnd":case"otransitionend":case"transitionend":this.transitionEnd(a);break;case"resize":Q()}d.stopPropagation&&a.stopPropagation()}},start:function(a){var b;x(a)?(b=a,a.preventDefault()):b=a.touches[0],B={x:b.pageX,y:b.pageY,time:+new Date},z=void 0,C={},x(a)?(N.addEventListener("mousemove",this,!1),N.addEventListener("mouseup",this,!1),N.addEventListener("mouseleave",this,!1)):(N.addEventListener("touchmove",this,!1),N.addEventListener("touchend",this,!1))},move:function(a){var b;if(x(a))b=a;else{if(a.touches.length>1||a.scale&&1!==a.scale)return;d.disableScroll&&a.preventDefault(),b=a.touches[0]}C={x:b.pageX-B.x,y:b.pageY-B.y},"undefined"==typeof z&&(z=!!(z||Math.abs(C.x)<Math.abs(C.y))),z||(a.preventDefault(),t(),d.continuous?(q(m(O-1),C.x+K[m(O-1)],0),q(O,C.x+K[O],0),q(m(O+1),C.x+K[m(O+1)],0)):(C.x=C.x/(!O&&C.x>0||O===J.length-1&&C.x<0?Math.abs(C.x)/L+1:1),q(O-1,C.x+K[O-1],0),q(O,C.x+K[O],0),q(O+1,C.x+K[O+1],0)))},end:function(a){var b=+new Date-B.time,c=Number(b)<250&&Math.abs(C.x)>20||Math.abs(C.x)>L/2,e=!O&&C.x>0||O===J.length-1&&C.x<0;d.continuous&&(e=!1);var f=Math.abs(C.x)/C.x;z||(c&&!e?(f<0?(d.continuous?(p(m(O-1),-L,0),p(m(O+2),L,0)):p(O-1,-L,0),p(O,K[O]-L,P),p(m(O+1),K[m(O+1)]-L,P),O=m(O+1)):(d.continuous?(p(m(O+1),L,0),p(m(O-2),-L,0)):p(O+1,L,0),p(O,K[O]+L,P),p(m(O-1),K[m(O-1)]+L,P),O=m(O-1)),k(n(),J[O],f)):d.continuous?(p(m(O-1),-L,P),p(O,0,P),p(m(O+1),L,P)):(p(O-1,-L,P),p(O,0,P),p(O+1,L,P))),x(a)?(N.removeEventListener("mousemove",R,!1),N.removeEventListener("mouseup",R,!1),N.removeEventListener("mouseleave",R,!1)):(N.removeEventListener("touchmove",R,!1),N.removeEventListener("touchend",R,!1))},transitionEnd:function(a){var b=parseInt(a.target.getAttribute("data-index"),10);b===O&&((D||d.autoRestart)&&u(),l(n(),J[O]))}};return h(),D&&s(),{setup:h,slide:function(a,b){t(),o(a,b)},prev:function(){t(),i()},next:function(){t(),j()},restart:u,stop:t,getPos:n,disable:v,enable:w,getNumSlides:function(){return M},kill:y}}}var a="object"==typeof self&&self.self===self&&self||"object"==typeof global&&global.global===global&&global||this,b=a.document;return(a.jQuery||a.Zepto)&&!function(a){a.fn.Swipe=function(b){return this.each(function(){a(this).data("Swipe",new Swipe(a(this)[0],b))})}}(a.jQuery||a.Zepto),Swipe});

Zepto(function() {
    if (!window.ONEGLOBAL) window.ONEGLOBAL = {};
    ONEGLOBAL.vueComment = null;
    ONEGLOBAL.vueRelate = null;
    ONEGLOBAL.vueImagePopup = null;
    ONEGLOBAL.vueBus = new Vue();

    //閸戣姤鏆熼妴浣稿弿鐏炩偓閸欐﹢鍣?
    // 娑撳秶鏁ap娴滃娆㈤敍瀵奺pto閻ㄥ墖ap娴滃娆㈢€圭偤妾紒鎴濈暰閸︹暈ody娑撳绱濋張澶屸敍闁繘妫舵０?
    var EVENT_CLICK_NAME = Zepto.browser.oneapp ? 'click' : 'click';
    var LAST_AUDIO_ID = 0;
    var LAST_RADIO_ID = 0;


    function initPage() {

        // handler濞夈劌鍞介崣濠呯殶閻?
        ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
            bridge.registerHandler('setPlayingAudio', function(audio) { setPlayingAudio(audio); });
            bridge.registerHandler('setNightMode', function(setting) { setNightMode(setting); });
            var lastContentPositionY = 0;
            bridge.registerHandler('gotoCommentsTop', function(data) {
                if (ONEGLOBAL._isInViewport(Zepto('.one-comments-box').get(0), false)) {
                    if (lastContentPositionY) {
                        window.scrollTo(0, lastContentPositionY);
                    }
                } else {
                    lastContentPositionY = Zepto(window).scrollTop();
                    var o = Zepto('.one-comments-box').offset();
                    window.scrollTo(0, o.top - 80);
                }
            });
            bridge.registerHandler('setPlayingRadio', function(radio) { setPlayingRadio(radio); });
            bridge.registerHandler('setPlayingMusic', function(music) { setPlayingMusic(music); });
            bridge.registerHandler('setComments', function(data) {
                Vue.set(ONEGLOBAL.vueComment, "loaded", true);
                if (!data || !data.comments && !data.comments.length) {
                    Vue.set(ONEGLOBAL.vueComment, "common_comments", []);
                    Vue.set(ONEGLOBAL.vueComment, "hot_comments", []);
                    return;
                }
                var hot_comments = [];
                var common_comments = [];
                var praised_comments = {};
                Zepto.each(data.praises, function(idx, item) {
                    praised_comments[item] = true;
                });
                Zepto.each(data.comments, function(idx, item) {
                    item.is_praised = praised_comments[item.id] ? true : false;
                    if (item.type == "1")
                        common_comments.push(item);
                    else
                        hot_comments.push(item);
                });
                Vue.set(ONEGLOBAL.vueComment, "common_comments", common_comments);
                Vue.set(ONEGLOBAL.vueComment, "hot_comments", hot_comments);
                Vue.set(ONEGLOBAL.vueComment, "goto", data.goto || "");

            });
            bridge.registerHandler('setRelates', function(data) {
                if (!data || !data.relates && !data.relates.length) {
                    Vue.set(ONEGLOBAL.vueRelate, "relates", []);
                    return;
                }
                Vue.set(ONEGLOBAL.vueRelate, "relates", data.relates);
            });
            bridge.registerHandler('setAuthorList', function(data) {
                if (!data || !data.author_list && !data.author_list.length) {
                    return;
                }
                Zepto.each(data.author_list, function(idx, item) {
                    console.log("set author follow -- " + item.user_id);
                    Zepto('.one-author-box-for-' + item.user_id + '').removeClass("one-following");
                    if (item.is_followed) {
                        Zepto('.one-author-box-for-' + item.user_id + '').addClass("one-following");
                    }
                });
            });
            bridge.registerHandler('setAuthorFollow', function(data) {
                if (!data || !data.user_id) {
                    return;
                }
                Zepto('.one-author-box-for-' + data.user_id + '').removeClass("one-following");
                if (data.is_followed) {
                    Zepto('.one-author-box-for-' + data.user_id + '').addClass("one-following");
                }
            });
        });
        if (!Zepto.browser.oneapp) {
            ONEGLOBAL._makeStatisticsForWap();
        }

        // 妞ょ敻娼伴棅鍏呯闁剧偓甯存径鍕倞
        var audios = [];
        Zepto('.one-music-box').each(function(idx) {
            var params = {
                type: '' + Zepto(this).data('type'),
                id: '' + Zepto(this).data('id'),
                name: '' + Zepto(this).data('name'),
                artistName: '' + Zepto(this).data('artistName'),
                source: '' + Zepto(this).data('source'),
                url: '' + Zepto(this).data('url'),
                album: '' + Zepto(this).data('album'),
                coverURL: '' + Zepto(this).data('coverUrl'),
                platform_name:'' + Zepto(this).data('platformName'),
                platform_icon:'' + Zepto(this).data('platformIcon'),

            };
            if(!params.platform_name){
                params.platform_name = {'ONE':'ONE', 'XIAMI':'閾忓墽鑳?,'ONEXIAMI': 'ONE閵夋槒娅ㄧ猾?'}[params.source];
                params.platform_icon = 'http://image.wufazhuce.com/music_copyright_'+{'ONE':2, 'XIAMI':1,'ONEXIAMI': 3}[params.source]+'.png'
            }
            audios.push(params);
        });

        ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
            bridge.callHandler('prepareAudios', { audios: audios }, function(data) {});
        });
        // 鐠囧嫯顔戦崚婵嗩潗閸?
        _initCommentsComponent();
        _initRelatesComponent();
        // 闂婂厖绠扮拠锔藉剰妞ら潧銇旈柈銊ㄦ祮閻╂绮嶆禒?
        //_initRotateComponent();
        //鐠囷附鍎忔い闈涱嚤閼割亝鐖憴浣风瑝閸欘垵顫嗘径鍕倞
        _initNavbarToggleComponent();
        if (!ONEGLOBAL.fullwebview && Zepto.browser.oneapp && /^8\.\d/.test(Zepto.os.version)) {
            Zepto('body').css("marginRight", "45px");
        }
        if (ONEGLOBAL.fullwebview && Zepto.browser.oneapp && Zepto.os.android && Zepto('.one-page-header-image').size() <= 0) {
            var androidNavibarHeight = '45px';
            Zepto('body').css('paddingTop', androidNavibarHeight);
        }
    }

    function initData() {
        ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
            bridge.callHandler('getOneStatus', {}, function(data) {
                console.log('call getOneStatus');
                console.log(data);
                if (data.audio) setPlayingAudio(data.audio); //濮濓絾鏋冮崘鍛畱闂婂厖绠伴棅鎶筋暥
                if (data.setting) setNightMode(data.setting);
                if (data.radio) setPlayingRadio(data.radio); //閻㈤潧褰?
                if (data.music) setPlayingMusic(data.music); //闂婂厖绠版０鎴︿壕閻ㄥ嫰鐓舵稊?
                if (data.readingAudio) ONEGLOBAL.vueBus.$emit("one.readingaudio.change", data.readingAudio); //閺堝锛愰梼鍛邦嚢
                if (!data.stat) data.stat = {};
                ONEGLOBAL._makeStatistics(data);
            });
        });
    }

    function _initRotateComponent() {
        /*
        闂婂厖绠版潪顒傛磸css娴狅絿鐖?
        <div id="planet">
        </div>
               #planet { 
            width:200px;
            height:200px;
            background: transparent url(http://cdn3.iconfinder.com/data/icons/nx11/Internet%20-%20Real.png) no-repeat center center;
        }

        #planet {
          -webkit-animation-name: rotate;
          -webkit-animation-duration:2s;
          -webkit-animation-iteration-count:infinite;
          -webkit-animation-timing-function:linear;
          -moz-animation-name: rotate;
          -moz-animation-duration:2s;
          -moz-animation-iteration-count:infinite;
          -moz-animation-timing-function:linear;
        }

        @-webkit-keyframes rotate {
          from {-webkit-transform:rotate(0deg);}
          to {  -webkit-transform:rotate(360deg);}
        }

        @-moz-keyframes rotate {
          from {-moz-transform:rotate(0deg);}
          to {  -moz-transform:rotate(360deg);}
        }

        #planet:hover {
          -webkit-animation-play-state:paused;
          -moz-animation-play-state:paused;
        } 
        */
        return false;
    }

    function _initCommentsComponent() {
        Vue.component('one-comment', {
            template: '#oneCommentTemplate',
            props: ['commentitem'],
            data: function() {
                return {
                    expand_status: "",
                    comment: this.commentitem,
                };
            },
            filters: {
                truncate: function(v) {
                    var newline = v.indexOf('\n');
                    return newline > 0 ? v.slice(0, newline) : v;
                },
                formatDate: function(v) {
                    return v.substr(0, v.length - 3).replace(/-/g, ".");
                },
            },
            created: function() {
                ONEGLOBAL.vueBus.$on("one.comment.praise.add", function(id) {
                    if (this.comment.id == id) {
                        this.comment.praisenum++;
                        this.comment.is_praised = true;
                    }
                }.bind(this));
                ONEGLOBAL.vueBus.$on("one.comment.praise.delete", function(id) {
                    if (this.comment.id == id) {
                        this.comment.praisenum--;
                        this.comment.is_praised = false;
                    }
                }.bind(this));
            },
            mounted: function() {
                var h = Zepto(this.$el).find(".one-comment-content").height();
                console.log("expand" + this.comment.id + "/" + this.expand_status + "/h=" + h);
                if (h > 130 && !this.expand_status) {
                    this.expand_status = "collapsed";
                }
            },

            methods: {
                quoteComment: function(comment) {
                    console.log("post comment");
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("quoteComment", {
                            quote: comment.user.user_name + "鐠囪揪绱? + comment.content,
                            user_name: comment.user.user_name,
                            content: comment.content,
                            id: comment.id
                        }, function() {

                        });
                    });
                    return false;
                },
                addCommentPraise: function(comment) {
                    console.log("add praise");
                    //comment.is_praised = true;
                    //comment.praisenum++;
                    ONEGLOBAL.vueBus.$emit('one.comment.praise.add', comment.id);
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("addCommentPraise", {
                            id: comment.id
                        }, function() {

                        });
                    });
                    return false;
                },
                delCommentPraise: function(comment) {
                    console.log("del praise");
                    //comment.is_praised = false;
                    //comment.praisenum--;
                    ONEGLOBAL.vueBus.$emit('one.comment.praise.delete', comment.id);
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("delCommentPraise", {
                            id: comment.id
                        }, function() {

                        });
                    });
                    return false;
                },
                showCommentMenu: function(comment, evt) {
                    var p = Zepto(evt.target).closest(".one-comment-box").get(0).getBoundingClientRect();
                    console.log(" show comment menu on Top=" + p.top + " Bottom=" + p.bottom);
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("showCommentMenu", {
                            comment: {
                                id: comment.id,
                                user_id: comment.user.user_id,
                                content: comment.content
                            },
                            position: {
                                top: "" + p.top,
                                bottom: "" + p.bottom
                            }
                        }, function() {

                        });
                    });
                },
                openUserHome: function(comment) {
                    console.log("show user home");
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("openUserHome", {
                            id: "" + comment.user.user_id
                        }, function() {

                        });
                    });
                    return false;
                },
                collapseCommentContent: function(type, comment, evt) {
                    this.expand_status = 'collapsed';
                },
                expandCommentContent: function(type, comment, evt) {
                    this.expand_status = 'expanded';
                },
            },
        });

        ONEGLOBAL.vueComment = new Vue({
            el: '.one-comments-box',
            data: {
                hot_comments: [],
                common_comments: [],
                loaded: false,
                goto: '',
            },
            created: function() {

            },
            updated: function() {
                console.log("goto comment = " + this.goto);
                if (this.goto == "top") {
                    var p = Zepto(".one-commoncomment-box").offset();
                    if (p) {
                        window.scrollTo(0, p.top - 80);
                    }
                } else if (this.goto == "bottom") {
                    Zepto(".one-commoncomment-box").get(0).scrollIntoView(false);
                }
            }
        });
    }

    function _initRelatesComponent() {
        ONEGLOBAL.vueRelate = new Vue({
            el: '.one-relates-box',
            data: {
                relates: [],
            },

            filters: {
                formatDate: function(v) {
                    return v.replace(/T|Z/g, ' ');
                },
                combineAuthor: function(v) {
                    var arr = [];
                    Zepto.each(v, function(idx, item) {
                        arr.push(item.user_name);
                    });
                    return arr.join("閵?");
                }
            },

            methods: {
                openRelateDetail: function(relate) {
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("openRelateDetail", {
                            id: "" + relate.content_id,
                            category: 0 + relate.category || 0,
                            serials: relate.serial_list,
                        }, function() {

                        });
                    });
                },
            }
        });
    }

    function _initNavbarToggleComponent() {
        if (!Zepto('.one-title-box').get(0)) { return true; }
        var isVisible = true;
        window.onscroll = function() {
            if (ONEGLOBAL._isInViewport(Zepto('.one-title-box').get(0), false)) {
                if (!isVisible) {
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler('toggleNavbar', { title: { visible: true, text: '' } }, function() {});
                    });
                    isVisible = true;
                }
            } else {
                if (isVisible) {
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler('toggleNavbar', { title: { visible: false, text: '' } }, function() {});
                    });
                    isVisible = false;
                }
            }
        };
    }

    function setPlayingAudio(audio) {
        Zepto('.one-music-box').each(function(idx) {
            if (audio.id && audio.id == Zepto(this).data('id')) {
                Zepto(this).addClass('one-playing');
            } else {
                Zepto(this).removeClass('one-playing');
            }
        });
        if(audio && audio.id) {
            ONEGLOBAL.vueBus.$emit("one.radio.change",{});
            ONEGLOBAL.vueBus.$emit("one.readingaudio.change",{});
            ONEGLOBAL.vueBus.$emit("one.music.change",{});
        }
    }

    function playAudio(audio) {
        if (!Zepto.browser.oneapp) { //M缁?
            var isContinuePlay = LAST_AUDIO_ID == audio.id;
            Zepto('.one-music-box').each(function(idx) {
                var jplayer = Zepto(this).find(".one-jplayer").get(0);
                var needPlay = audio.id && audio.id == Zepto(this).data('id');
                if (jplayer && needPlay) {
                    if (!isContinuePlay) jplayer.currentTime = 0;
                    jplayer.play();
                }
            });
            LAST_AUDIO_ID = audio.id;
        } else {
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('playAudio', audio, function() {});
            });
        }

    }

    function stopAudio(audio) {
        if (!Zepto.browser.oneapp) {
            var jplayer = null;
            Zepto('.one-music-box').each(function(idx) {
                jplayer = Zepto(this).find(".one-jplayer").get(0);
                if (jplayer) {
                    jplayer.pause();
                }
            });
        } else {
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('stopAudio', {}, function() {});
            });
        }
    }

    function setNightMode(setting) {
        if (Zepto('.one-page-special').size() > 0) {
            return;
        }
        if (setting.nightMode) {
            Zepto('body').addClass('one-night-mode');
        } else {
            Zepto('body').removeClass('one-night-mode');
        }
    }

    function playGif(boxElem) {
        if (Zepto(boxElem).hasClass('one-playing')) return;
        Zepto(boxElem).addClass('one-playing');
        var gifSrc = Zepto(boxElem).find('.one-gif').data('gifSrc');
        if (!gifSrc) return;
        Zepto(boxElem).find('.one-gif').attr('src', gifSrc);
    }

    function playVideo(params) {
        if (!Zepto.browser.oneapp) { //M缁?
            Zepto('.one-video-box').each(function(idx) {
                if (Zepto(this).data('url') != params.url) return;
                var jplayer = Zepto(this).find(".one-jplayer").get(0);
                if (jplayer) {
                    jplayer.play();
                }
            });
        } else {
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('playVideo', params, function() {});
            });
        }
    }


    function setPlayingRadio(radio) {
        Zepto('.one-radio-header-box').each(function(idx) {
            if (radio.id && radio.id == Zepto(this).data('id')) {
                Zepto(this).addClass('one-playing');
            } else {
                Zepto(this).removeClass('one-playing');
            }
        });
        ONEGLOBAL.vueBus.$emit("one.radio.change", radio);
        if(radio && radio.id) {
            ONEGLOBAL.vueBus.$emit("one.music.change",{});
            ONEGLOBAL.vueBus.$emit("one.readingaudio.change",{});
            ONEGLOBAL.vueBus.$emit("one.audio.change",{});
        }
    }

    function playRadio(radio) {
        if (!Zepto.browser.oneapp) { //M缁?
            var isContinuePlay = LAST_RADIO_ID == radio.id;
            Zepto('.one-radio-header-box').each(function(idx) {
                var jplayer = Zepto(this).find(".one-jplayer").get(0);
                var needPlay = radio.id && radio.id == Zepto(this).data('id');
                if (jplayer && needPlay) {
                    if (!isContinuePlay) jplayer.currentTime = 0;
                    jplayer.play();
                }
            });
            LAST_RADIO_ID = radio.id;
        } else {
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('playRadio', radio, function() {});
            });
        }

    }

    function stopRadio(radio) {
        if (!Zepto.browser.oneapp) {
            var jplayer = null;
            Zepto('.one-radio-header-box').each(function(idx) {
                jplayer = Zepto(this).find(".one-jplayer").get(0);
                if (jplayer) {
                    jplayer.pause();
                }
            });
        } else {
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('stopRadio', {}, function() {});
            });
        }
    }


    function setPlayingMusic(music) {
        Zepto('.one-music-header-box').each(function(idx) {
            //console.log(music);
            if (music.id && music.id == Zepto(this).data('id')) {
                //console.log("setplayingmusic play..." + music);
                Zepto(this).addClass('one-playing');
            } else {
                //console.log("setplayingmusic stop..." + music);
                Zepto(this).removeClass('one-playing');
            }
        });
        ONEGLOBAL.vueBus.$emit("one.music.change", music);
        if(music && music.id) {
            ONEGLOBAL.vueBus.$emit("one.radio.change",{});
            ONEGLOBAL.vueBus.$emit("one.readingaudio.change",{});
            ONEGLOBAL.vueBus.$emit("one.audio.change",{});
        }
    }

    function playMusic(music) {
        if (!Zepto.browser.oneapp) { //M缁?
        } else {
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('playMusic', music, function() {});
            });
        }

    }

    function stopMusic(music) {
        if (!Zepto.browser.oneapp) {} else {
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('stopMusic', music, function() {});
            });
        }
    }



    function processPageLinks() {
        Zepto('a').each(function(idx) {
            if (!Zepto.browser.oneapp) return true;
            var href = this.href;
            //Zepto(this).attr("href", "java"+"script"+":void("+idx+")");
            Zepto(this).removeAttr("href");
            Zepto(this).attr("onehref", href);
        });

        Zepto('a').on(EVENT_CLICK_NAME, function(e) {
            if (!Zepto.browser.oneapp) return true;
            var href = Zepto(this).attr("onehref");
            if (/^http(s?):/.test(href)) { // http://www.sina.com.cn/view?id=5
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    bridge.callHandler('openURL', { url: href }, function() {});
                });
            } else if (/^one:/.test(href)) { // one://essay/2323
                var arr = href.split('/');
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    bridge.callHandler('openOne', { type: arr[2], id: arr[3] }, function() {});
                });
            } else {
                //alert(this.href);
            }
            return false;
        });
    }

    function processPageImages() {
        var imgs = [];
        var triggerClass = "ONEJS-IMAGE-TRIGGER";
        Zepto('img').forEach(function(img, idx) {
            var src = Zepto(img).attr('src');
            if (!src) return;
            if (Zepto(img).hasClass('one-image-exclude')) return;
            Zepto(img).removeAttr('width height style'); //缁夊娅庨弮褍鍞寸€归€涜厬閻ㄥ嫬娴橀悧鍥с亣鐏忓繗顔曠純?
            var params = {
                url: src,
                originalUrl: src,
                note: '',
                author: '',
                source: '',
            };
            if (Zepto(img).data('author')) params.author = '' + Zepto(img).data('author');
            if (Zepto(img).data('source')) params.source = '' + Zepto(img).data('source');
            if (Zepto(img).data('originalSrc')) params.originalUrl = '' + Zepto(img).data('originalSrc');
            var sibling = Zepto(img).next();
            if (Zepto(sibling).hasClass("one-image-note")) {
                params.note = Zepto(sibling).text();
            } else {
                sibling = Zepto(img).parent().next();
                if (Zepto(sibling).hasClass("one-image-note")) {
                    params.note = Zepto(sibling).text();
                }
            }
            if (!params.note) params.note = '';
            Zepto(img).addClass(triggerClass);
            Zepto(img).data("imageIndex", imgs.length);
            imgs.push(params);

        });
        ONEGLOBAL.vueImagePopup = new Vue({
            el: '.one-image-popup',
            data: {
                images: imgs,
                selectedIndex: -1,
            },
            created: function() {
                var that = this;
                Zepto('.one-image-popup').css('display', '');
            },
            filters: {
                formatAuthor: function(v) {
                    return v && v.length ? '娴ｆ粏鈧拑绱? + v : " ";
                },
                formatSource: function(v) {
                    return v && v.length ? '閺夈儲绨敍?' + v : " ";
                },
            },
            methods: {
                closePopup: function() {
                    this.selectedIndex = -1;
                },
            }
        });
        Zepto('img.' + triggerClass).on(EVENT_CLICK_NAME, function(e) {
            var idx = Zepto(this).data("imageIndex");
            //console.log("click image " + idx);
            if (Zepto.browser.oneapp) {
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    //for app version 4.1
                    bridge.callHandler('showImage', imgs[idx], function() {});
                    //for app version 4.2
                    bridge.callHandler('showImages', { images: imgs, position: "" + idx }, function() {});
                });
            } else {
                Vue.set(ONEGLOBAL.vueImagePopup, 'selectedIndex', idx);
            }
        });
    }

    function processPageGifs() {
        Zepto('.one-gif-box').each(function(idx) {
            Zepto(this).find('img').addClass("one-image-exclude");
            if (!ONEGLOBAL.fullwebview && Zepto.browser.oneapp && Zepto.os.android) {
                return true;
            } else {
                if (idx < 3) {
                    return playGif(this);
                }
            }
            var that = this;
            Zepto(this).on(EVENT_CLICK_NAME, function(e) {
                playGif(that);
            });
        });
    }

    function processPageAudios() {
        Zepto('.one-music-box').each(function(idx) {
            var that = this;
            if (!Zepto.browser.oneapp) {
                if (Zepto(that).data("source") == "ONE") {
                    Zepto(this).append('<audio class="one-jplayer" loop="loop" preload="metadata"><source src="' + Zepto(that).data('url') + '"></audio>');
                } else {
                    Zepto(this).html('<img src="http://image.wufazhuce.com/xiami-music-placeholder.png" alt="闂堟糠NE闂婂厖绠伴崡鐘辩秴閸?" class="one-image-exclude">').removeClass('one-music-box').css('margin-bottom', '30px');
                }
            }
            Zepto(this).find('img').addClass("one-image-exclude");
            Zepto(this).on(EVENT_CLICK_NAME, function(e) {
                var params = {
                    type: '' + Zepto(that).data('type'),
                    id: '' + Zepto(that).data('id'),
                    name: '' + Zepto(that).data('name'),
                    artistName: '' + Zepto(that).data('artistName'),
                    source: '' + Zepto(that).data('source'),
                    url: '' + Zepto(that).data('url'),
                    album: '' + Zepto(that).data('album'),
                    coverURL: '' + Zepto(that).data('coverUrl'),
                    platform_name:'' + Zepto(that).data('platformName'),
                    platform_icon:'' + Zepto(that).data('platformIcon'),
                };
                if (Zepto(this).hasClass('one-playing')) {
                    setPlayingAudio({});
                    stopAudio({});
                } else {
                    setPlayingAudio(params);
                    playAudio(params);
                }
            });
        });
    }

    function processPageVideos() {
        Zepto('.one-video-box').each(function(idx) {
            Zepto(this).find('img').addClass("one-image-exclude");
            if (!Zepto.browser.oneapp) {
                if (Zepto.os.ios) {
                    Zepto(this).find('.one-video-cover').html('<video webkit-playsinline width="100%" style="width:100%;height:auto;" class="vplayinside notaplink" x-webkit-airplay controls  preload="metadata" loop="loop" src="' + Zepto(this).data('url') + '" poster="' + Zepto(this).data('coverUrl') + '"></video>');
                } else {
                    Zepto(this).find('.one-video-cover').html('<video width="100%"  style="width:100%;height:auto;" preload="metadata" controls loop="loop" src="' + Zepto(this).data('url') + '" poster="' + Zepto(this).data('coverUrl') + '"></video>');
                }
            } else {
                var that = this;
                Zepto(this).find('.one-play-btn').on(EVENT_CLICK_NAME, function(e) {
                    var params = {
                        name: '' + Zepto(that).data('name'),
                        authorName: '' + Zepto(that).data('authorName'),
                        source: '' + Zepto(that).data('source'),
                        url: '' + Zepto(that).data('url'),
                        coverURL: '' + Zepto(that).data('coverUrl'),
                    };
                    playVideo(params);
                });
            }
        });
    }

    function processPageRadios() {
        Zepto('.one-radio-header-box').each(function(idx) {
            var that = this;
            if (!Zepto.browser.oneapp) {
                Zepto(this).append('<audio class="one-jplayer" loop="loop" preload="metadata"><source src="' + Zepto(that).data('url') + '"></audio>');
            }
            Zepto(this).find('img').addClass("one-image-exclude");
            Zepto(this).on(EVENT_CLICK_NAME, function(e) {
                if (Zepto(that).hasClass('one-playing')) {
                    setPlayingRadio({});
                    stopRadio({});
                } else {
                    var params = {
                        id: '' + Zepto(that).data('id'),
                    };
                    setPlayingRadio(params);
                    playRadio(params);
                }
            });
            //鏉╂稑鍙嗛悽闈涘酱鐠囷附鍎忔い鍨閿涘矁鍤滈崝銊︽尡閺€鍓ф暩閸?
            var params1 = {
                id: '' + Zepto(that).data('id'),
            };
            setPlayingRadio(params1);
            playRadio(params1);
            //ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
            //    bridge.callHandler("showToast", { text: '缂傛挸鍟挎稉顓涒偓锔光偓?', time: 1500 }, function() {});
            //});
        });
    }

    function processPageAuthors() {
        Zepto('.one-author-box').each(function(idx) {
            Zepto(this).find('img').addClass("one-image-exclude");
            var that = this;

            var userId = "" + Zepto(that).data('id');
            Zepto(this).find('.one-author-home-trigger').on(EVENT_CLICK_NAME, function(e) {
                if (!Zepto.browser.oneapp) {
                    return;
                }
                var params = {
                    id: userId,
                };
                console.log("open author home : " + params);
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    bridge.callHandler("openAuthorHome", params, function() {});
                });
            });
            Zepto(this).find('.one-author-follow-trigger').on(EVENT_CLICK_NAME, function(e) {
                e.stopPropagation();
                if (!Zepto.browser.oneapp) {
                    return;
                }
                var params = {
                    id: userId,
                };
                console.log("follow author  : " + params);
                Zepto(that).addClass("one-following");
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    bridge.callHandler("followAuthor", params, function() {});
                });
            });
            Zepto(this).find('.one-author-unfollow-trigger').on(EVENT_CLICK_NAME, function(e) {
                e.stopPropagation();
                if (!Zepto.browser.oneapp) {
                    return;
                }
                var params = {
                    id: userId,
                };
                console.log("unfollow author  : " + params);
                Zepto(that).removeClass("one-following");
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    bridge.callHandler("unfollowAuthor", params, function() {});
                });
            });
        });
    }

    function processPageReadingAudios() {
        Vue.component('one-reading-audio', {
            template: '#oneReadingAudioTemplate',
            props: ['dataText', 'dataTime', 'dataId', 'dataCategory'],
            data: function() {
                return {
                    isPlaying: false,
                    percentage: "1",
                    remainingTime: this.dataTime,
                };
            },
            created: function() {
                var that = this;
                ONEGLOBAL.vueBus.$on("one.readingaudio.change", function(data) {
                    if (!data || data.id != this.dataId || data.category != that.dataCategory) {
                        that.isPlaying = false;
                        return;
                    }
                    that.percentage = data.percentage;
                    if (data.remainingTime) {
                        that.remainingTime = data.remainingTime;
                    }
                    that.isPlaying = data.isPlaying;
                }.bind(this));
            },
            filters: {
                truncate: function(v) {
                    var newline = v.indexOf('\n');
                    return newline > 0 ? v.slice(0, newline) : v;
                },
                formatDate: function(v) {
                    return v.substr(0, v.length - 3).replace(/-/g, ".");
                },
            },
            methods: {
                togglePlaying: function() {
                    this.isPlaying = !this.isPlaying;
                    var name = this.isPlaying ? 'playReadingAudio' : 'stopReadingAudio';
                    var that = this;
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler(name, { id: that.dataId, category: that.dataCategory }, function(data) {});
                    });

                },
            },
        });

        ONEGLOBAL.vueReadingAudio = new Vue({
            el: '.onevue-readingaudio-box',
        });
        // todo 閻愮懓鍤幘顓熸杹閺冨爼娓剁憰浣筋啎缂冾噣銆夐棃顫瑐閻ㄥ嫬鍙炬禒鏍э紣闂婂磭娈戦悩鑸碘偓渚婄礉閸欏倽鈧兒etPlayingRadio閸戣姤鏆?
        ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
            bridge.registerHandler('setReadingAudio', function(data) {
                if (!data) return;
                ONEGLOBAL.vueBus.$emit("one.readingaudio.change", data);
            });
        });
    }
    function setReadingAudio(audio){
        //todo
        if(audio && audio.isPlaying){
            ONEGLOBAL.vueBus.$emit("one.____todo.change", {});
        }
        return;
    }

    function processPageSerials() {
        Zepto('.one-serial-nav-btn').on(EVENT_CLICK_NAME, function(evt) {
            if (Zepto(this).hasClass('one-serial-nav-disable')) return;
            var name = Zepto(this).hasClass('one-serial-nav-prev') ? "openSerialPrev" : "openSerialNext";
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler(name, {}, function(data) {});
            });
        });
        Zepto('.one-serial-nav-ids-btn').on(EVENT_CLICK_NAME, function(evt) {
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('openSerialTOC', {}, function(data) {});
            });
        });
    }

    function processPageMusic() {
        Zepto('.one-music-header-box').each(function(idx) {
            var that = this;
            if (!Zepto.browser.oneapp) {
                //Zepto(this).append('<audio class="one-jplayer" loop="loop" preload="metadata"><source src="'+Zepto(that).data('url')+'"></audio>');
            }
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                var music = {
                    id: ONEGLOBAL.content_id,
                    audio_url: Zepto(that).data("audioUrl"),
                    audio_platform:Zepto(that).data("audioPlatform"),
                };
                bridge.callHandler('prepareMusic', music, function() {});
            });
            Zepto(this).find('img').addClass("one-image-exclude");
            Zepto(this).find('.one-music-header-icons').on(EVENT_CLICK_NAME, function(e) {
        
                var params = {
                    id: '' + Zepto(that).data('id'),
                    title: '' + Zepto(that).data('title'),
                    author: '' + Zepto(that).data('author'),
                    audio_url: '' + Zepto(that).data('audioUrl'),
                    audio_platform: '' + Zepto(that).data('audioPlatform'),
                    platform_name: '' + Zepto(that).data('platformName'),
                    platform_icon: '' + Zepto(that).data('platformIcon'),
                };
                if(ONEGLOBAL.music_exception && /^1|3$/.test(params.audio_platform)){//閾忓墽鑳岄崪瀛玁E-閾忓墽鑳?
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("showToast", { text: ONEGLOBAL.music_exception, time: 1500 }, function () { });
                    }); 
                    return;
                }
                if (Zepto(that).hasClass('one-playing')) {
                    setPlayingMusic({});
                    stopMusic(params);
                } else {
                    setPlayingMusic(params);
                    playMusic(params);
                }
            });
        });
        Zepto('.one-music-lyrics-btn').on(EVENT_CLICK_NAME, function(e) {
            if(typeof oneDataArticle == 'undefined') return;
            if(!oneDataArticle) return;
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('openMusicInfo', oneDataArticle, function(data) {});
            });
        });
    }

    function processPageMovie() {
        Zepto('.one-movie-header-aspectratio-box').each(function(idx, item) {
            var that = this;
            if (!Zepto.browser.oneapp) {

                //Zepto(this).append('<audio class="one-jplayer" loop="loop" preload="metadata"><source src="'+Zepto(that).data('url')+'"></audio>');
            }
            Zepto(this).find('img').addClass("one-image-exclude");
            Zepto(this).find('.one-movie-open-btn').on(EVENT_CLICK_NAME, function(e) {
                var video = {
                    id        : Zepto(that).data('id'), 
                    title     : Zepto(that).data('title'),
                    video_url : Zepto(that).data('videoUrl'),
                };
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    bridge.callHandler('playMovie', video, function(data) {});
                });
            });
            var imgs = [];
            Zepto(this).find('.swiper-slide').forEach(function(img, idx) {
                var src = Zepto(img).data('src');
                if (!src) return;
                var params = {
                    url: src,
                    originalUrl: src,
                    note: '',
                    author: '',
                    source: '',
                };
                Zepto(img).data("imageIndex", imgs.length);
                imgs.push(params);

            });
            Zepto(this).find('.swiper-wrapper').on(EVENT_CLICK_NAME, function(e) {
                var idx = parseInt(Zepto(item).data('activeImageIndex'),10);
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    bridge.callHandler('showImages', { images: imgs, position: "" + idx }, function() {});
                });
            });
            var setCaption = function(swiper){
                Zepto(item).find('.one-movie-swipe-caption .current').text(swiper.realIndex + 1);
                Zepto(item).find('.one-movie-swipe-caption .total').text(swiper.slides.length - 2);
                Zepto(item).find('.one-movie-open-btn').css('display', swiper.realIndex>0 ? 'none' : 'block');
                Zepto(item).data('activeImageIndex', swiper.realIndex);
                return false;
            };
            ONEGLOBAL['objSwipe' + idx] = new Swiper(item, {loop:true, onInit:setCaption, onSlideChangeEnd:setCaption });
        });
        Zepto('.one-movie-info-btn').on(EVENT_CLICK_NAME, function(e) {
            if(typeof oneDataArticle == 'undefined') return;
            if(!oneDataArticle) return;
            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                bridge.callHandler('openMovieInfo', oneDataArticle, function(data) {});
            });
        });
    }

    function processPageSpecial() {
        var oneSpecialCardBase = {
            props: ['article'],
            data: function() {
                return {
                    isPraised: false,
                    praisenum: this.article.like_count,
                };
            },
            computed: {
                formatted_author_avatar: function() {
                    if (!this.article.author_list || !this.article.author_list.length) return "";
                    return this.article.author_list[0].web_url;
                },
                formatted_author_name: function() {
                    var arr = [];
                    for (var i = 0; i < this.article.author_list.length; i++) {
                        arr.push(this.article.author_list[i].user_name);
                    }
                    if (!arr.length) return " ";
                    if (arr.length == 1) return arr[0];
                    return arr[0] + "缁?" + arr.length + "娴?";
                },
                formatted_tag: function() {
                    if (this.article.tag_list && this.article.tag_list.length > 0) {
                        return this.article.tag_list[0].title;
                    } else {
                        return 'Type' + this.article.content_type;
                    }
                },
                formatted_content_type: function() {
                    var c = parseInt(this.article.content_type, 10);
                    return isNaN(c) ? 0 : c;
                },
            },
            filters: {
                formatPraisenum: function(num) {
                    if (num<=0) return 0;
                    if (num >= 100000) return '99999+';
                    return num;
                },
            },
            created: function() {
                var that = this;
                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                    bridge.callHandler("getArticlePraise", {
                        id: that.article.content_id,
                        category: that.formatted_content_type
                    }, function(data) {
                        that.isPraised = data.praised;
                    });
                });
                ONEGLOBAL.vueBus.$on('one.article.praise.change', function(data){
                    if(!data || data.id!=that.article.content_id || data.category != that.article.category ) return;
                    that.isPraised = data.praised;
                    that.praisenum += data.praised ? 1 : -1;
                });
            },
            mounted: function() {},
            methods: {
                openRelate: function(article) {
                    var that = this;
                    if (Zepto.browser.oneapp) {
                        document.location = article.share_url;
                    }
                    var statData = {event_id:"", map:{topic_id: ONEGLOBAL.content_id}};
                    var statConf = {
                        1: ['article ', 'essay_id'],
                        2: ['serial', 'serial_id'],
                        3: ['question', 'question_id'],
                        4: ['music', 'music_id'],
                        5: ['movie', 'movie_id'],
                        8: ['radio', 'radio_id'],
                    };
                    if(/^1|2|3|4|5|8$/.test(article.content_type)){
                        statData.event_id = 'topic_'+statConf[article.content_type][0];
                        statData.map[statConf[article.content_type][1]] = article.content_id;
                    } 
                    if(statData.event_id){
                        ONEGLOBAL.setupWebViewJavascriptBridge(function (bridge) {
                            bridge.callHandler('saveStatData', statData, function () { });
                        });
                    }
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("openRelateDetail", {
                            id: "" + article.content_id,
                            category: that.formatted_content_type,
                            serials: article.serial_list,
                            source: 'topic',
                            source_id: ONEGLOBAL.content_id || '',
                        }, function() {

                        });
                    });
                },
                showShare: function(article) {
                    var that = this;
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("showShare", {
                            id: "" + article.content_id,
                            category: that.formatted_content_type,
                            share_list: article.share_list,
                            source: 'topic',
                            source_id: ONEGLOBAL.content_id || '',
                        }, function() {
                            //
                        });
                    });
                },
                togglePraise: function(article) {
                    this.isPraised = !this.isPraised;
                    this.praisenum += this.isPraised ? 1 : -1;
                    var that = this;
                    ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                        bridge.callHandler("setPraise", {
                            id: "" + article.content_id,
                            category: that.formatted_content_type,
                            praised: that.isPraised,
                            source: 'topic',
                            source_id: ONEGLOBAL.content_id || '',
                        }, function() {

                        });
                    });
                },
            },
        };
        ONEGLOBAL.vueSpecialCards = new Vue({
            el: '.one-special-cards-box',
            data: {
                theme: 0,
                articles: typeof oneDataArticles == 'undefined' ? [] : oneDataArticles,
            },
            created: function() {
                ONEGLOBAL.setupWebViewJavascriptBridge(function (bridge) {
                    bridge.registerHandler("setArticlePraise", function(data) {
                        ONEGLOBAL.vueBus.$emit('one.article.praise.change', data);
                    });
                }); 
            },
            components: {
                'one-special-card-essay': {
                    extends: oneSpecialCardBase,
                    template: '#oneSpecialCardEssayTemplate',
                },
                'one-special-card-question': {
                    extends: oneSpecialCardBase,
                    template: '#oneSpecialCardEssayTemplate',
                },
                'one-special-card-serial': {
                    extends: oneSpecialCardBase,
                    template: '#oneSpecialCardEssayTemplate',
                },
                'one-special-card-movie': {
                    extends: oneSpecialCardBase,
                    template: '#oneSpecialCardMovieTemplate',
                    methods: {
                        playMovie: function(article) {
                            var video = {
                                id: article.content_id,
                                title: article.subtitle,
                                video_url: article.video_url,
                            };
                            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                                bridge.callHandler('playMovie', video, function() {});
                            });
                        },
                    },
                },
                'one-special-card-music': {
                    extends: oneSpecialCardBase,
                    template: '#oneSpecialCardMusicTemplate',
                    props: [],
                    data: function() {
                        return {
                            isPlaying: false,
                        };
                    },
                    computed: {
                        url_background_with_copyright: function() {
                            var watermark = {
                                "0": "aHR0cDovL2ltYWdlLnd1ZmF6aHVjZS5jb20vbXVzaWNfY29weXJpZ2h0XzEucG5n",
                                "1": "aHR0cDovL2ltYWdlLnd1ZmF6aHVjZS5jb20vbXVzaWNfY29weXJpZ2h0XzIucG5n",
                                "2": "aHR0cDovL2ltYWdlLnd1ZmF6aHVjZS5jb20vbXVzaWNfY29weXJpZ2h0XzMucG5n"
                            };
                            return this.article.img_url + '?imageMogr2/thumbnail/600x600%7Cwatermark/1/image/' + watermark[this.article.audio_platform - 1] + '/gravity/SouthEast/dx/25/dy/25';
                        },
                    },
                    created: function() {
                        var that = this;
                        ONEGLOBAL.vueBus.$on('one.music.change', function(music) {
                            that.isPlaying = ( music && music.id && music.id == that.article.content_id);
                        });
                        ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                            var music = {
                                id: that.article.content_id,
                                audio_url: that.article.audio_url,
                                audio_platform: that.article.audio_platform,
                            };
                            bridge.callHandler('prepareMusic', music, function() {});
                        });
                    },
                    methods: {
                        playMusic: function(article) {
                            var music = {
                                id: article.content_id,
                                title: article.music_name,
                                author: article.audio_author,
                                audio_url: article.audio_url,
                                audio_platform: article.audio_platform,
                                platform_name: article.platform_name,
                                platform_icon: article.platform_icon,
                            };
                            if(ONEGLOBAL.music_exception && /^1|3$/.test(article.audio_platform)){//閾忓墽鑳岄崪瀛玁E-閾忓墽鑳?
                                ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                                    bridge.callHandler("showToast", { text: ONEGLOBAL.music_exception, time: 1500 }, function () { });
                                }); 
                                return;
                            }
                            setPlayingMusic(music);
                            playMusic(music);
                            //this.isPlaying = true;
                            //this.toggleMusic(article, 'playMusic');
                        },
                        stopMusic: function(article) {
                            //this.isPlaying = false;
                            //this.toggleMusic(article, 'stopMusic');
                            setPlayingMusic({});
                            stopMusic({});
                        },
                        toggleMusic: function(article, act) {

                            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                                bridge.callHandler(act, music, function() {});
                            });
                        }
                    },
                },
                'one-special-card-radio': {
                    extends: oneSpecialCardBase,
                    template: '#oneSpecialCardRadioTemplate',
                    data: function() {
                        return {
                            isPlaying: false,
                        };
                    },
                    created: function() {
                        var that = this;
                        ONEGLOBAL.vueBus.$on('one.radio.change', function(radio) {
                            that.isPlaying = (radio && radio.id && radio.id == that.article.content_id);
                        });
                    },
                    methods: {
                        playRadio: function(article) {
                            var radio = {
                                id: article.content_id,
                                title: article.title,
                                author: article.author.user_name,
                                audio_url: article.audio_url,
                            };
                            setPlayingRadio(radio);
                            playRadio(radio);
                            //this.isPlaying = true;
                            //this.toggleRadio(article, 'playRadio');
                        },
                        stopRadio: function(article) {
                            //this.isPlaying = false;
                            //this.toggleRadio(article, 'stopRadio');
                            setPlayingRadio({});
                            stopRadio({});
                        },
                        toggleRadio: function(article, act) {
                            var radio = {
                                id: article.content_id,
                                title: article.title,
                                author: article.author.user_name,
                                audio_url: article.audio_url,
                            };
                            
                            ONEGLOBAL.setupWebViewJavascriptBridge(function(bridge) {
                                bridge.callHandler(act, radio, function() {});
                            });
                        },
                    },
                },
            },
        });
    }

    //=========
    initPage();

    //濮濓絾鏋冮崘鍛啇婢跺嫮鎮?
    processPageGifs();
    processPageLinks();
    processPageAudios();
    processPageVideos();

    //娴ｆ粏鈧懎灏崺鐔奉槱閻?
    processPageAuthors();

    //閻㈤潧褰寸拠锔藉剰妞ゅ吀绗傞弬鍦暩閸欐澘顦╅悶?
    processPageRadios();

    //鏉╃偠娴囩拠锔藉剰妞ら潧顕遍懜顏堝劥閸掑棗顦╅悶?
    processPageSerials();

    //闂婂厖绠扮拠锔藉剰妞ら潧銇旈柈銊ヮ槱閻?
    processPageMusic();

    //閻㈤潧濂栫拠锔藉剰妞ら潧銇旈柈銊ヮ槱閻?
    processPageMovie();

    //鐠囷附鍎忔い鍨箒婢逛即妲勭拠璇差槱閻?
    processPageReadingAudios();

    //娑撴捇顣界拠锔藉剰妞ら潧顦╅悶?
    processPageSpecial();

    //濮濓絾鏋冮崘鍛啇闁插瞼娈戦崶鍓у婢跺嫮鎮?(韫囧懘銆忛弨鐐付閸氬函绱掗敍渚婄磼閿?)
    processPageImages();

    initData();

});