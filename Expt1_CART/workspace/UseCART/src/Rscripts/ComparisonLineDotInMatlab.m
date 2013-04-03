
x11=[9	29]
y11=[0.149	0.077]
x12=[9 	18 	27 	29 ]
y12=[0.269	0.116	0.084	0.097]
y12p=polyval(polyfit(x12,y12,2),x12);

subplot(3,2,1);
plot(x11,y11,'bo', x12, y12, 'k*', x12,y12p,'k-');
set(gca,'ygrid','on');
ylabel('Fault Rate (x100%)');
title('Apache');
legend('FWH and PWH','Our Approach')


x21=[11	62]
y21=[0.079	0.074]
x22=[11 	22 	33 	62]
y22=[0.057	0.045	0.04	0.033]
y22p=polyval(polyfit(x22,y22,2),x22);

subplot(3,2,2);
plot(x21,y21,'bo', x22, y22, 'k*', x22,y22p,'k-');
set(gca,'ygrid','on');
title('LLVM');


x31=[16 81]
y31=[0.296	0.179]
x32=[16 	32 	48 	81]
y32=[0.151	0.085	0.072	0.064]
y32p=polyval(polyfit(x32,y32,2),x32);

subplot(3,2,3);
plot(x31,y31,'bo', x32, y32, 'k*', x32,y32p,'k-');
set(gca,'ygrid','on');
ylabel('Fault Rate (x100%)');
title('x264');


x41=[18	139]
y41=[0.441	0.039]
x42=[18 	36 	54 	139]
y42=[1.1238	0.9831	0.4678	0.078]
y42p=polyval(polyfit(x42,y42,2),x42);

subplot(3,2,4);
plot(x41,y41,'bo', x42, y42, 'k*', x42,y42p,'k-');
set(gca,'ygrid','on');
title('BerkeleyDBC');

x51=[26	48]
y51=[0.177	0.085]
x52=[26 	52 	78 	48]
y52=[0.032	0.022	0.026	0.027]
y52p=polyval(polyfit(x52,y52,2),x52);

subplot(3,2,5);
plot(x51,y51,'bo', x52, y52, 'k*', x52,y52p,'k-');
set(gca,'ygrid','on');
xlabel('#Measurements'),ylabel('Fault Rate (x100%)');
title('BerkeleyDBJ');


x61=[39	566]
y61=[0.078	0.093]
x62=[39 	78 	117 	566 ]
y62=[0.08	0.081	0.076	0.072]
y62p=polyval(polyfit(x62,y62,2),x62);

subplot(3,2,6);
plot(x61,y61,'bo', x62, y62, 'k*', x62,y62p,'k-');

set(gca,'ygrid','on');
xlabel('#Measurements');
title('SQLite');

print -depsc -r400 dotline.eps
