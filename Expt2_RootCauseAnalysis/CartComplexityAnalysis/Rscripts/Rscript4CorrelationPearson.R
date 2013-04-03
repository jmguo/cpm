## Apache

pdf("./ApacheCorrelationPearsonVis.pdf")
ds <- read.csv("./ApacheCartComplexity.csv", header = TRUE, sep = ",")

## BDBC

pdf("./BDBCCorrelationPearsonVis.pdf")
ds <- read.csv("./BDBCCartComplexity.csv", header = TRUE, sep = ",")

## BDBJ

pdf("./BDBJCorrelationPearsonVis.pdf")
ds <- read.csv("./BDBJCartComplexity.csv", header = TRUE, sep = ",")

## LLVM

pdf("./LLVMCorrelationPearsonVis.pdf")
ds <- read.csv("./LLVMCartComplexity.csv", header = TRUE, sep = ",")

## SQL

pdf("./SQLCorrelationPearsonVis.pdf")
ds <- read.csv("./SQLCartComplexity.csv", header = TRUE, sep = ",")

## X264

pdf("./X264CorrelationPearsonVis.pdf")
ds <- read.csv("./X264CartComplexity.csv", header = TRUE, sep = ",")


## common part

require(ellipse, quietly=TRUE)

cor.data <- cor(ds[c("SampleSize", "AvgBreadth","AvgDepth","Accuracy")], use="pairwise", method="pearson")

#cor.ord <- order(cor.data[1,]) 
#using the same order for each case
cor.ord <- seq(cor.data[1,])
cor <- cor.data[cor.ord, cor.ord]

print(cor)

colors= colorRampPalette(c("red", "white", "blue"))(11)

plotcorr(cor, col= colors[5*cor + 6])

#title(main="Apache Correlation using Pearson")

dev.off()
