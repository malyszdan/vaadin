function war_MyAppWidgetset(){var O='bootstrap',P='begin',Q='gwt.codesvr.war.MyAppWidgetset=',R='gwt.codesvr=',S='war.MyAppWidgetset',T='startup',U='DUMMY',V=0,W=1,X='iframe',Y='javascript:""',Z='position:absolute; width:0; height:0; border:none; left: -1000px;',$=' top: -1000px;',_='CSS1Compat',ab='<!doctype html>',bb='',cb='<html><head><\/head><body><\/body><\/html>',db='undefined',eb='DOMContentLoaded',fb=50,gb='Chrome',hb='eval("',ib='");',jb='script',kb='javascript',lb='moduleStartup',mb='moduleRequested',nb='war_MyAppWidgetset',ob='Failed to load ',pb='head',qb='meta',rb='name',sb='war.MyAppWidgetset::',tb='::',ub='gwt:property',vb='content',wb='=',xb='gwt:onPropertyErrorFn',yb='Bad handler "',zb='" for "gwt:onPropertyErrorFn"',Ab='gwt:onLoadErrorFn',Bb='" for "gwt:onLoadErrorFn"',Cb='#',Db='?',Eb='/',Fb='img',Gb='clear.cache.gif',Hb='baseUrl',Ib='war.MyAppWidgetset.nocache.js',Jb='base',Kb='//',Lb='modernie',Mb='MSIE',Nb='Trident',Ob='yes',Pb='none',Qb='user.agent',Rb='webkit',Sb='safari',Tb='msie',Ub=10,Vb=11,Wb='ie10',Xb=9,Yb='ie9',Zb=8,$b='ie8',_b='gecko',ac='gecko1_8',bc=2,cc=3,dc=4,ec='selectingPermutation',fc='war.MyAppWidgetset.devmode.js',gc='CCA3E18AF160BAFA5317789547D701F8',hc=':1',ic=':2',jc=':3',kc=':4',lc=':5',mc=':',nc='.cache.js',oc='loadExternalRefs',pc='end',qc='http:',rc='file:',sc='_gwt_dummy_',tc='__gwtDevModeHook:war.MyAppWidgetset',uc='Ignoring non-whitelisted Dev Mode URL: ',vc=':moduleBase';var o=window;var p=document;r(O,P);function q(){var a=o.location.search;return a.indexOf(Q)!=-1||a.indexOf(R)!=-1}
function r(a,b){if(o.__gwtStatsEvent){o.__gwtStatsEvent({moduleName:S,sessionId:o.__gwtStatsSessionId,subSystem:T,evtGroup:a,millis:(new Date).getTime(),type:b})}}
war_MyAppWidgetset.__sendStats=r;war_MyAppWidgetset.__moduleName=S;war_MyAppWidgetset.__errFn=null;war_MyAppWidgetset.__moduleBase=U;war_MyAppWidgetset.__softPermutationId=V;war_MyAppWidgetset.__computePropValue=null;war_MyAppWidgetset.__getPropMap=null;war_MyAppWidgetset.__installRunAsyncCode=function(){};war_MyAppWidgetset.__gwtStartLoadingFragment=function(){return null};war_MyAppWidgetset.__gwt_isKnownPropertyValue=function(){return false};war_MyAppWidgetset.__gwt_getMetaProperty=function(){return null};var s=null;var t=o.__gwt_activeModules=o.__gwt_activeModules||{};t[S]={moduleName:S};war_MyAppWidgetset.__moduleStartupDone=function(e){var f=t[S].bindings;t[S].bindings=function(){var a=f?f():{};var b=e[war_MyAppWidgetset.__softPermutationId];for(var c=V;c<b.length;c++){var d=b[c];a[d[V]]=d[W]}return a}};var u;function v(){w();return u}
function w(){if(u){return}var a=p.createElement(X);a.src=Y;a.id=S;a.style.cssText=Z+$;a.tabIndex=-1;p.body.appendChild(a);u=a.contentDocument;if(!u){u=a.contentWindow.document}u.open();var b=document.compatMode==_?ab:bb;u.write(b+cb);u.close()}
function A(k){function l(a){function b(){if(typeof p.readyState==db){return typeof p.body!=db&&p.body!=null}return /loaded|complete/.test(p.readyState)}
var c=b();if(c){a();return}function d(){if(!c){c=true;a();if(p.removeEventListener){p.removeEventListener(eb,d,false)}if(e){clearInterval(e)}}}
if(p.addEventListener){p.addEventListener(eb,d,false)}var e=setInterval(function(){if(b()){d()}},fb)}
function m(c){function d(a,b){a.removeChild(b)}
var e=v();var f=e.body;var g;if(navigator.userAgent.indexOf(gb)>-1&&window.JSON){var h=e.createDocumentFragment();h.appendChild(e.createTextNode(hb));for(var i=V;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(e.createTextNode(j.substring(W,j.length-W)))}h.appendChild(e.createTextNode(ib));g=e.createElement(jb);g.language=kb;g.appendChild(h);f.appendChild(g);d(f,g)}else{for(var i=V;i<c.length;i++){g=e.createElement(jb);g.language=kb;g.text=c[i];f.appendChild(g);d(f,g)}}}
war_MyAppWidgetset.onScriptDownloaded=function(a){l(function(){m(a)})};r(lb,mb);var n=p.createElement(jb);n.src=k;if(war_MyAppWidgetset.__errFn){n.onerror=function(){war_MyAppWidgetset.__errFn(nb,new Error(ob+code))}}p.getElementsByTagName(pb)[V].appendChild(n)}
war_MyAppWidgetset.__startLoadingFragment=function(a){return D(a)};war_MyAppWidgetset.__installRunAsyncCode=function(a){var b=v();var c=b.body;var d=b.createElement(jb);d.language=kb;d.text=a;c.appendChild(d);c.removeChild(d)};function B(){var c={};var d;var e;var f=p.getElementsByTagName(qb);for(var g=V,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(rb),k;if(j){j=j.replace(sb,bb);if(j.indexOf(tb)>=V){continue}if(j==ub){k=i.getAttribute(vb);if(k){var l,m=k.indexOf(wb);if(m>=V){j=k.substring(V,m);l=k.substring(m+W)}else{j=k;l=bb}c[j]=l}}else if(j==xb){k=i.getAttribute(vb);if(k){try{d=eval(k)}catch(a){alert(yb+k+zb)}}}else if(j==Ab){k=i.getAttribute(vb);if(k){try{e=eval(k)}catch(a){alert(yb+k+Bb)}}}}}__gwt_getMetaProperty=function(a){var b=c[a];return b==null?null:b};s=d;war_MyAppWidgetset.__errFn=e}
function C(){function e(a){var b=a.lastIndexOf(Cb);if(b==-1){b=a.length}var c=a.indexOf(Db);if(c==-1){c=a.length}var d=a.lastIndexOf(Eb,Math.min(c,b));return d>=V?a.substring(V,d+W):bb}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=p.createElement(Fb);b.src=a+Gb;a=e(b.src)}return a}
function g(){var a=__gwt_getMetaProperty(Hb);if(a!=null){return a}return bb}
function h(){var a=p.getElementsByTagName(jb);for(var b=V;b<a.length;++b){if(a[b].src.indexOf(Ib)!=-1){return e(a[b].src)}}return bb}
function i(){var a=p.getElementsByTagName(Jb);if(a.length>V){return a[a.length-W].href}return bb}
function j(){var a=p.location;return a.href==a.protocol+Kb+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==bb){k=h()}if(k==bb){k=i()}if(k==bb&&j()){k=e(p.location.href)}k=f(k);return k}
function D(a){if(a.match(/^\//)){return a}if(a.match(/^[a-zA-Z]+:\/\//)){return a}return war_MyAppWidgetset.__moduleBase+a}
function F(){var f=[];var g=V;function h(a,b){var c=f;for(var d=V,e=a.length-W;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
var i=[];var j=[];function k(a){var b=j[a](),c=i[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(s){s(a,d,b)}throw null}
j[Lb]=function(){{var a=o.navigator.userAgent;if(a.indexOf(Mb)==-1&&a.indexOf(Nb)!=-1){return Ob}return Pb}};i[Lb]={none:V,yes:W};j[Qb]=function(){var a=navigator.userAgent.toLowerCase();var b=p.documentMode;if(function(){return a.indexOf(Rb)!=-1}())return Sb;if(function(){return a.indexOf(Tb)!=-1&&(b>=Ub&&b<Vb)}())return Wb;if(function(){return a.indexOf(Tb)!=-1&&(b>=Xb&&b<Vb)}())return Yb;if(function(){return a.indexOf(Tb)!=-1&&(b>=Zb&&b<Vb)}())return $b;if(function(){return a.indexOf(_b)!=-1||b>=Vb}())return ac;return bb};i[Qb]={gecko1_8:V,ie10:W,ie8:bc,ie9:cc,safari:dc};__gwt_isKnownPropertyValue=function(a,b){return b in i[a]};war_MyAppWidgetset.__getPropMap=function(){var a={};for(var b in i){if(i.hasOwnProperty(b)){a[b]=k(b)}}return a};war_MyAppWidgetset.__computePropValue=k;o.__gwt_activeModules[S].bindings=war_MyAppWidgetset.__getPropMap;r(O,ec);if(q()){return D(fc)}var l;try{h([Pb,ac],gc);h([Ob,ac],gc+hc);h([Pb,Wb],gc+ic);h([Pb,$b],gc+jc);h([Pb,Yb],gc+kc);h([Pb,Sb],gc+lc);l=f[k(Lb)][k(Qb)];var m=l.indexOf(mc);if(m!=-1){g=parseInt(l.substring(m+W),Ub);l=l.substring(V,m)}}catch(a){}war_MyAppWidgetset.__softPermutationId=g;return D(l+nc)}
function G(){if(!o.__gwt_stylesLoaded){o.__gwt_stylesLoaded={}}r(oc,P);r(oc,pc)}
B();war_MyAppWidgetset.__moduleBase=C();t[S].moduleBase=war_MyAppWidgetset.__moduleBase;var H=F();if(o){var I=!!(o.location.protocol==qc||o.location.protocol==rc);o.__gwt_activeModules[S].canRedirect=I;function J(){var b=sc;try{o.sessionStorage.setItem(b,b);o.sessionStorage.removeItem(b);return true}catch(a){return false}}
if(I&&J()){var K=tc;var L=o.sessionStorage[K];if(!/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/.test(L)){if(L&&(window.console&&console.log)){console.log(uc+L)}L=bb}if(L&&!o[K]){o[K]=true;o[K+vc]=C();var M=p.createElement(jb);M.src=L;var N=p.getElementsByTagName(pb)[V];N.insertBefore(M,N.firstElementChild||N.children[V]);return false}}}G();r(O,pc);A(H);return true}
war_MyAppWidgetset.succeeded=war_MyAppWidgetset();