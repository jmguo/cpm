

x=[1 2 3 4];

y1=[0.0260 0.0300 0.0240 0.0240];
y1p=polyval(polyfit(x,y1,2),x);

y2=[0.034 0.042 0.036 0.034];
y2p=polyval(polyfit(x,y2,2),x);

y3=[0.024 0.02 0.026 0.022];
y3p=polyval(polyfit(x,y3,2),x);

y4=[0.026 0.042 0.026 0.034];
y4p=polyval(polyfit(x,y4,2),x);

y5=[0.034 0.036 0.034 0.032];
y5p=polyval(polyfit(x,y5,2),x);

y6=[0.044 0.042 0.044 0.09];
y6p=polyval(polyfit(x,y6,2),x);

plot(x,y1,'gx',x,y1p,'g-',x, y2,'yx',x,y2p,'y-',x,y3,'mx',x,y3p,'m-',x,y4,'rx',x,y4p,'r-',x,y5,'cx',x,y5p,'c-',x,y6,'kx',x,y6p,'k-');

axis([1 4 0.02 0.091]);
set(gca,'xtick',[1:1:4]);

legend('Apache','LLVM','x264','BerkeleyDBC', 'BerkeleyDBJ', 'SQLite');
xlabel('Size of Training Set'), ylabel('Time consumption (s)');;

print -depsc -r400 time.eps;