import{a4 as de,a5 as fe,b as he,a6 as pe,h as ae,j as me,e as _e,o as Q,k as se,a7 as ie,T as p,a8 as $e,m as W,a9 as be,aa as ne,p as oe,q as ue,_ as ye,K as ge,M as re,N as ve,G as ce,E as Me,P as q,Q as Se,l as De,S as u,V as ke,W as we,X as Ie,x,y as G,Y as Ce,s as Oe,Z as Ee,$ as xe,a0 as Te,a1 as Ye,a2 as Le,a3 as ze}from"./index-7cb4597a.js";import{E as He,b as je}from"./select-80979e20.js";import{E as Ne,a as Ve,b as Be}from"./form-item-2ab73a10.js";import{c as We}from"./jt1078-config-b409f47d.js";var le={exports:{}};(function(y,j){(function(d,$){y.exports=$()})(fe,function(){var d=1e3,$=6e4,T=36e5,Y="millisecond",m="second",v="minute",L="hour",C="day",A="week",S="month",R="quarter",I="year",O="date",l="Invalid Date",D=/^(\d{4})[-/]?(\d{1,2})?[-/]?(\d{0,2})[Tt\s]*(\d{1,2})?:?(\d{1,2})?:?(\d{1,2})?[.:]?(\d+)?$/,J=/\[([^\]]+)]|Y{1,4}|M{1,4}|D{1,2}|d{1,4}|H{1,2}|h{1,2}|a|A|m{1,2}|s{1,2}|Z{1,2}|SSS/g,te={name:"en",weekdays:"Sunday_Monday_Tuesday_Wednesday_Thursday_Friday_Saturday".split("_"),months:"January_February_March_April_May_June_July_August_September_October_November_December".split("_"),ordinal:function(a){var t=["th","st","nd","rd"],e=a%100;return"["+a+(t[(e-20)%10]||t[e]||t[0])+"]"}},U=function(a,t,e){var s=String(a);return!s||s.length>=t?a:""+Array(t+1-s.length).join(e)+a},X={s:U,z:function(a){var t=-a.utcOffset(),e=Math.abs(t),s=Math.floor(e/60),n=e%60;return(t<=0?"+":"-")+U(s,2,"0")+":"+U(n,2,"0")},m:function a(t,e){if(t.date()<e.date())return-a(e,t);var s=12*(e.year()-t.year())+(e.month()-t.month()),n=t.clone().add(s,S),r=e-n<0,i=t.clone().add(s+(r?-1:1),S);return+(-(s+(e-n)/(r?n-i:i-n))||0)},a:function(a){return a<0?Math.ceil(a)||0:Math.floor(a)},p:function(a){return{M:S,y:I,w:A,d:C,D:O,h:L,m:v,s:m,ms:Y,Q:R}[a]||String(a||"").toLowerCase().replace(/s$/,"")},u:function(a){return a===void 0}},z="en",E={};E[z]=te;var g=function(a){return a instanceof F},N=function a(t,e,s){var n;if(!t)return z;if(typeof t=="string"){var r=t.toLowerCase();E[r]&&(n=r),e&&(E[r]=e,n=r);var i=t.split("-");if(!n&&i.length>1)return a(i[0])}else{var c=t.name;E[c]=t,n=c}return!s&&n&&(z=n),n||!s&&z},f=function(a,t){if(g(a))return a.clone();var e=typeof t=="object"?t:{};return e.date=a,e.args=arguments,new F(e)},o=X;o.l=N,o.i=g,o.w=function(a,t){return f(a,{locale:t.$L,utc:t.$u,x:t.$x,$offset:t.$offset})};var F=function(){function a(e){this.$L=N(e.locale,null,!0),this.parse(e)}var t=a.prototype;return t.parse=function(e){this.$d=function(s){var n=s.date,r=s.utc;if(n===null)return new Date(NaN);if(o.u(n))return new Date;if(n instanceof Date)return new Date(n);if(typeof n=="string"&&!/Z$/i.test(n)){var i=n.match(D);if(i){var c=i[2]-1||0,h=(i[7]||"0").substring(0,3);return r?new Date(Date.UTC(i[1],c,i[3]||1,i[4]||0,i[5]||0,i[6]||0,h)):new Date(i[1],c,i[3]||1,i[4]||0,i[5]||0,i[6]||0,h)}}return new Date(n)}(e),this.$x=e.x||{},this.init()},t.init=function(){var e=this.$d;this.$y=e.getFullYear(),this.$M=e.getMonth(),this.$D=e.getDate(),this.$W=e.getDay(),this.$H=e.getHours(),this.$m=e.getMinutes(),this.$s=e.getSeconds(),this.$ms=e.getMilliseconds()},t.$utils=function(){return o},t.isValid=function(){return this.$d.toString()!==l},t.isSame=function(e,s){var n=f(e);return this.startOf(s)<=n&&n<=this.endOf(s)},t.isAfter=function(e,s){return f(e)<this.startOf(s)},t.isBefore=function(e,s){return this.endOf(s)<f(e)},t.$g=function(e,s,n){return o.u(e)?this[s]:this.set(n,e)},t.unix=function(){return Math.floor(this.valueOf()/1e3)},t.valueOf=function(){return this.$d.getTime()},t.startOf=function(e,s){var n=this,r=!!o.u(s)||s,i=o.p(e),c=function(B,M){var H=o.w(n.$u?Date.UTC(n.$y,M,B):new Date(n.$y,M,B),n);return r?H:H.endOf(C)},h=function(B,M){return o.w(n.toDate()[B].apply(n.toDate("s"),(r?[0,0,0,0]:[23,59,59,999]).slice(M)),n)},_=this.$W,b=this.$M,w=this.$D,P="set"+(this.$u?"UTC":"");switch(i){case I:return r?c(1,0):c(31,11);case S:return r?c(1,b):c(0,b+1);case A:var V=this.$locale().weekStart||0,K=(_<V?_+7:_)-V;return c(r?w-K:w+(6-K),b);case C:case O:return h(P+"Hours",0);case L:return h(P+"Minutes",1);case v:return h(P+"Seconds",2);case m:return h(P+"Milliseconds",3);default:return this.clone()}},t.endOf=function(e){return this.startOf(e,!1)},t.$set=function(e,s){var n,r=o.p(e),i="set"+(this.$u?"UTC":""),c=(n={},n[C]=i+"Date",n[O]=i+"Date",n[S]=i+"Month",n[I]=i+"FullYear",n[L]=i+"Hours",n[v]=i+"Minutes",n[m]=i+"Seconds",n[Y]=i+"Milliseconds",n)[r],h=r===C?this.$D+(s-this.$W):s;if(r===S||r===I){var _=this.clone().set(O,1);_.$d[c](h),_.init(),this.$d=_.set(O,Math.min(this.$D,_.daysInMonth())).$d}else c&&this.$d[c](h);return this.init(),this},t.set=function(e,s){return this.clone().$set(e,s)},t.get=function(e){return this[o.p(e)]()},t.add=function(e,s){var n,r=this;e=Number(e);var i=o.p(s),c=function(b){var w=f(r);return o.w(w.date(w.date()+Math.round(b*e)),r)};if(i===S)return this.set(S,this.$M+e);if(i===I)return this.set(I,this.$y+e);if(i===C)return c(1);if(i===A)return c(7);var h=(n={},n[v]=$,n[L]=T,n[m]=d,n)[i]||1,_=this.$d.getTime()+e*h;return o.w(_,this)},t.subtract=function(e,s){return this.add(-1*e,s)},t.format=function(e){var s=this,n=this.$locale();if(!this.isValid())return n.invalidDate||l;var r=e||"YYYY-MM-DDTHH:mm:ssZ",i=o.z(this),c=this.$H,h=this.$m,_=this.$M,b=n.weekdays,w=n.months,P=n.meridiem,V=function(M,H,Z,ee){return M&&(M[H]||M(s,r))||Z[H].slice(0,ee)},K=function(M){return o.s(c%12||12,M,"0")},B=P||function(M,H,Z){var ee=M<12?"AM":"PM";return Z?ee.toLowerCase():ee};return r.replace(J,function(M,H){return H||function(Z){switch(Z){case"YY":return String(s.$y).slice(-2);case"YYYY":return o.s(s.$y,4,"0");case"M":return _+1;case"MM":return o.s(_+1,2,"0");case"MMM":return V(n.monthsShort,_,w,3);case"MMMM":return V(w,_);case"D":return s.$D;case"DD":return o.s(s.$D,2,"0");case"d":return String(s.$W);case"dd":return V(n.weekdaysMin,s.$W,b,2);case"ddd":return V(n.weekdaysShort,s.$W,b,3);case"dddd":return b[s.$W];case"H":return String(c);case"HH":return o.s(c,2,"0");case"h":return K(1);case"hh":return K(2);case"a":return B(c,h,!0);case"A":return B(c,h,!1);case"m":return String(h);case"mm":return o.s(h,2,"0");case"s":return String(s.$s);case"ss":return o.s(s.$s,2,"0");case"SSS":return o.s(s.$ms,3,"0");case"Z":return i}return null}(M)||i.replace(":","")})},t.utcOffset=function(){return 15*-Math.round(this.$d.getTimezoneOffset()/15)},t.diff=function(e,s,n){var r,i=this,c=o.p(s),h=f(e),_=(h.utcOffset()-this.utcOffset())*$,b=this-h,w=function(){return o.m(i,h)};switch(c){case I:r=w()/12;break;case S:r=w();break;case R:r=w()/3;break;case A:r=(b-_)/6048e5;break;case C:r=(b-_)/864e5;break;case L:r=b/T;break;case v:r=b/$;break;case m:r=b/d;break;default:r=b}return n?r:o.a(r)},t.daysInMonth=function(){return this.endOf(S).$D},t.$locale=function(){return E[this.$L]},t.locale=function(e,s){if(!e)return this.$L;var n=this.clone(),r=N(e,s,!0);return r&&(n.$L=r),n},t.clone=function(){return o.w(this.$d,this)},t.toDate=function(){return new Date(this.valueOf())},t.toJSON=function(){return this.isValid()?this.toISOString():null},t.toISOString=function(){return this.$d.toISOString()},t.toString=function(){return this.$d.toUTCString()},a}(),k=F.prototype;return f.prototype=k,[["$ms",Y],["$s",m],["$m",v],["$H",L],["$W",C],["$M",S],["$y",I],["$D",O]].forEach(function(a){k[a[1]]=function(t){return this.$g(t,a[0],a[1])}}),f.extend=function(a,t){return a.$i||(a(t,F,f),a.$i=!0),f},f.locale=N,f.isDayjs=g,f.unix=function(a){return f(1e3*a)},f.en=E[z],f.Ls=E,f.p={},f})})(le);var Ae=le.exports;const Fe=de(Ae),Pe=he({type:{type:String,values:["primary","success","warning","info","danger","default"],default:"default"},underline:{type:Boolean,default:!0},disabled:{type:Boolean,default:!1},href:{type:String,default:""},icon:{type:pe}}),Je={click:y=>y instanceof MouseEvent},Ue=["href"],Ke=ae({name:"ElLink"}),Ze=ae({...Ke,props:Pe,emits:Je,setup(y,{emit:j}){const d=y,$=me("link"),T=_e(()=>[$.b(),$.m(d.type),$.is("disabled",d.disabled),$.is("underline",d.underline&&!d.disabled)]);function Y(m){d.disabled||j("click",m)}return(m,v)=>(Q(),se("a",{class:oe(W(T)),href:m.disabled||!m.href?void 0:m.href,onClick:Y},[m.icon?(Q(),ie(W(be),{key:0},{default:p(()=>[(Q(),ie($e(m.icon)))]),_:1})):ne("v-if",!0),m.$slots.default?(Q(),se("span",{key:1,class:oe(W($).e("inner"))},[ue(m.$slots,"default")],2)):ne("v-if",!0),m.$slots.icon?ue(m.$slots,"icon",{key:2}):ne("v-if",!0)],10,Ue))}});var qe=ye(Ze,[["__file","/home/runner/work/element-plus/element-plus/packages/components/link/src/link.vue"]]);const Ge=ge(qe);function Qe(y={}){return re({url:`/api/dashboard/proxy/${y.instanceId}/api/dashboard-client/jt1078/sessions?withSubscribers=true`,method:"get",params:y})}function Re(y={}){return re({url:`/api/dashboard/proxy/${y.instanceId}/api/dashboard-client/jt1078/subscribers/${y.subscriberId}`,method:"delete"})}function Xe(y={}){return re({url:`/api/dashboard/proxy/${y.instanceId}/api/dashboard-client/jt1078/sessions/${y.sessionId}`,method:"delete"})}const et={p4:""},rt=ae({__name:"Jt1078SessionList",setup(y){var I,O;const j=ve(),d=ce({instanceId:"",page:1,rows:0,sim:"",pageSize:5,terminalId:"",version:"all"}),$=ce({total:0,data:[]});Me(()=>{v()});const T=(I=j.params)==null?void 0:I.instanceId,Y=(O=j.params)==null?void 0:O.sim;T||q.error("instanceId is empty"),typeof T=="string"&&(d.instanceId=T),typeof Y=="string"&&(d.terminalId=Y);const m=()=>{d.version="all",d.terminalId="",v()},v=async()=>{try{const l=await Qe({...d});Se(l)?($.data=l.data.records,$.total=l.data.total):(console.log(l),q.error(l.msg))}catch(l){q.error(l)}},L=l=>{d.page=l,v()},C=l=>{d.pageSize=l,v()},A=async l=>{const D=await Re({instanceId:j.params.instanceId,subscriberId:l.id});D.code===0&&(l.closed=!0,q.success(D.msg))},S=async l=>{const D=await Xe({instanceId:j.params.instanceId,sessionId:l.id});D.code===0&&(l.closed=!0,q.success(D.msg))},R=({row:l})=>(l==null?void 0:l.createdAt)<Fe().subtract(30,"m").format("YYYY-MM-DD HH:mm:ss")?"warning-row":"";return(l,D)=>{const J=Ye,te=we,U=He,X=Ne,z=Ve,E=Be,g=Le,N=Ge,f=ze,o=je,F=Ie;return Q(),se(ke,null,[De("nav",et,[u(te,{"separator-icon":W(Ce)},{default:p(()=>[u(J,{to:"/"},{default:p(()=>[x("Dashboard")]),_:1}),u(J,null,{default:p(()=>[x("1078SessionList")]),_:1}),u(J,null,{default:p(()=>[x(G(d.instanceId)+"11",1)]),_:1})]),_:1},8,["separator-icon"])]),u(F,{class:"box-card terminal-list-box",style:{"border-radius":"10px"}},{header:p(()=>[u(E,{inline:"",size:"small",onSubmit:D[1]||(D[1]=Oe(()=>{},["prevent"]))},{default:p(()=>[u(X,{label:"终端ID / SIM"},{default:p(()=>[u(U,{modelValue:d.sim,"onUpdate:modelValue":D[0]||(D[0]=k=>d.sim=k),placeholder:"终端ID / SIM",onKeyup:Ee(v,["enter"])},null,8,["modelValue","onKeyup"])]),_:1}),u(X,null,{default:p(()=>[u(z,{type:"primary",onClick:v,icon:W(xe)},{default:p(()=>[x("查询")]),_:1},8,["icon"]),u(z,{onClick:m,icon:W(Te)},{default:p(()=>[x("重置")]),_:1},8,["icon"])]),_:1})]),_:1})]),default:p(()=>[u(f,{data:$.data,border:"",stripe:"","w-full":""},{default:p(()=>[u(g,{type:"index",width:"50"}),u(g,{type:"expand"},{default:p(({row:k})=>[u(f,{data:k.subscribers,border:"","row-class-name":R},{default:p(()=>[u(g,{label:"订阅时间",prop:"createdAt"}),u(g,{label:"终端ID / SIM",prop:"sim"}),u(g,{label:"Desc",prop:"desc"}),u(g,{label:"Metadata","show-overflow-tooltip":{effect:"light",popperClass:"tip-content"}},{default:p(({row:a})=>[x(G(JSON.stringify(a.metadata)),1)]),_:2},1024),u(g,{label:"操作",align:"center"},{default:p(({row:a})=>[u(N,{type:"primary",onClick:t=>A(a),disabled:a.closed},{default:p(()=>[x(G(a.closed?"已关闭":"关闭订阅"),1)]),_:2},1032,["onClick","disabled"])]),_:2},1024)]),_:2},1032,["data"])]),_:1}),u(g,{prop:"id",label:"SubscriberId",width:"180"}),u(g,{prop:"lastCommunicateTime",label:"最近一次通信时间"}),u(g,{prop:"channel",label:"通道"},{default:p(({row:k})=>{var a;return[x(G((a=W(We).find(t=>t.channel===k.channel))==null?void 0:a.location),1)]}),_:1}),u(g,{label:"操作",align:"center"},{default:p(({row:k})=>[u(N,{type:"primary",onClick:a=>S(k),disabled:k.closed},{default:p(()=>[x(G(k.closed?"已关闭":"关闭通道"),1)]),_:2},1032,["onClick","disabled"])]),_:1})]),_:1},8,["data"]),u(o,{mt8:"","page-sizes":[5,10,20,30,50,100,200],"current-page":d.page,"page-size":d.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:$.total,onPagination:$.data,onSizeChange:C,onCurrentChange:L},null,8,["current-page","page-size","total","onPagination"])]),_:1})],64)}}});export{rt as default};
