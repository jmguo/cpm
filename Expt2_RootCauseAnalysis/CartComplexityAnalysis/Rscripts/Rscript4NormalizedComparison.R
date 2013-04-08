library(ggplot2)

## Apache

ds <- read.csv("../ApacheCartComplexityNormalized.csv", header = TRUE, sep = ",")

p <- ggplot(data=ds, aes(x=SampleSize, y=NormalizedValue, colour = factor(Kind), shape = factor(Kind), linetype=factor(Kind)))

p1<- p + geom_point(size=3) + stat_smooth(method="lm", se=FALSE, size =1)

p1<- p1 + scale_x_continuous(breaks=c(9, 18, 27, 29), labels=c("N", "2*N", "3*N", "M"))+ labs(colour="", shape="", linetype="") + theme(legend.position="top", legend.direction="horizontal") + xlab("Sample Size") + ylab("Normalized Values") 

p1<- p1 + theme(text = element_text(size=12))

ggsave("ApacheCartComplexityNormalizedVis.pdf")

## BDBC

ds <- read.csv("../BDBCCartComplexityNormalized.csv", header = TRUE, sep = ",")

p <- ggplot(data=ds, aes(x=SampleSize, y=NormalizedValue, colour = factor(Kind), shape = factor(Kind), linetype=factor(Kind)))

p1<- p + geom_point(size=3) + stat_smooth(method="lm", se=FALSE, size =1)

p1<- p1 + scale_x_continuous(breaks=c(18, 36, 54, 139), labels=c("N", "2*N", "3*N", "M")) + labs(colour="", shape="", linetype="", size="") + theme(legend.position="top", legend.direction="horizontal") + xlab("Sample Size") + ylab("Normalized Values") 

p1<- p1 + theme(text = element_text(size=12))

ggsave("BDBCCartComplexityNormalizedVis.pdf")

## BDBJ

ds <- read.csv("../BDBJCartComplexityNormalized.csv", header = TRUE, sep = ",")

p <- ggplot(data=ds, aes(x=SampleSize, y=NormalizedValue, colour = factor(Kind), shape = factor(Kind), linetype=factor(Kind)))

p1<- p + geom_point(size=3) + stat_smooth(method="lm", se=FALSE, size =1)

p1<- p1 + scale_x_continuous(breaks=c(26, 48, 52, 78), labels=c("N", "M", "2*N", "3*N")) + labs(colour="", shape="", linetype="") + theme(legend.position="top", legend.direction="horizontal") + xlab("Sample Size") + ylab("Normalized Values") 

p1<- p1 + theme(text = element_text(size=12))

ggsave("BDBJCartComplexityNormalizedVis.pdf")

## LLVM

ds <- read.csv("../LLVMCartComplexityNormalized.csv", header = TRUE, sep = ",")

p <- ggplot(data=ds, aes(x=SampleSize, y=NormalizedValue, colour = factor(Kind), shape = factor(Kind), linetype=factor(Kind)))

p1<- p + geom_point(size=3) + stat_smooth(method="lm", se=FALSE, size =1)

p1<- p1 + scale_x_continuous(breaks=c(11,22,33,62), labels=c("N", "2*N", "3*N", "M")) + labs(colour="", shape="", linetype="") + theme(legend.position="top", legend.direction="horizontal") + xlab("Sample Size") + ylab("Normalized Values") 

p1<- p1 + theme(text = element_text(size=12))

ggsave("LLVMCartComplexityNormalizedVis.pdf")

## SQL

ds <- read.csv("../SQLCartComplexityNormalized.csv", header = TRUE, sep = ",")

p <- ggplot(data=ds, aes(x=SampleSize, y=NormalizedValue, colour = factor(Kind), shape = factor(Kind), linetype=factor(Kind)))

p1<- p + geom_point(size=3) + stat_smooth(method="lm", se=FALSE, size =1)

p1<- p1 + scale_x_continuous(breaks=c(39,78,117,566), labels=c("N", "2*N", "3*N", "M")) + labs(colour="", shape="", linetype="") + theme(legend.position="top", legend.direction="horizontal") + xlab("Sample Size") + ylab("Normalized Values")  

p1<- p1 + theme(text = element_text(size=12))

ggsave("SQLCartComplexityNormalizedVis.pdf")


## X264

ds <- read.csv("../X264CartComplexityNormalized.csv", header = TRUE, sep = ",")

p <- ggplot(data=ds, aes(x=SampleSize, y=NormalizedValue, colour = factor(Kind), shape = factor(Kind), linetype=factor(Kind)))

p1<- p + geom_point(size=3) + stat_smooth(method="lm", se=FALSE, size =1)

p1<- p1 + scale_x_continuous(breaks=c(16,32,48,81), labels=c("N", "2*N", "3*N", "M")) + labs(colour="", shape="", linetype="") + theme(legend.position="top", legend.direction="horizontal") + xlab("Sample Size") + ylab("Normalized Values")  

p1<- p1 + theme(text = element_text(size=12))

ggsave("X264CartComplexityNormalizedVis.pdf")