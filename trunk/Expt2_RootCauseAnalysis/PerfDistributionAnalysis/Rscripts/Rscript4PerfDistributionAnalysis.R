## Apache

dspvset <- read.csv("../DataSet/PerfDistribution/ApachePerfValues.csv", header = TRUE, sep = ",")

pdf("../DataSet/PerfDistribution/ApachePerfDistribution.pdf")

par(mfrow=c(3, 2))

colnames <- c("N", "2*N", "3*N", "M", "All", "X100")

for (i in 1:5) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(840, 2640), breaks=seq(840, 2640, 200), main= colnames[i], ylab="Frequency", xlab="Performance", col="grey90", border=TRUE)
   rug(na.omit(dspvset[,i]))
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="red")
   
}

dev.off()

## BDBC

dspvset <- read.csv("../DataSet/PerfDistribution/BDBCPerfValues.csv", header = TRUE, sep = ",")

pdf("../DataSet/PerfDistribution/BDBCPerfDistribution.pdf")

par(mfrow=c(3, 2))

colnames <- c("N", "2*N", "3*N", "M", "All", "X100")

for (i in 1:5) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(0, 40), breaks=seq(0, 40, 4), main= colnames[i], ylab="Frequency", xlab="Performance", col="grey90", border=TRUE)
   rug(na.omit(dspvset[,i]))
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="red")
   
}

dev.off()

## BDBJ

dspvset <- read.csv("../DataSet/PerfDistribution/BDBJPerfValues.csv", header = TRUE, sep = ",")

pdf("../DataSet/PerfDistribution/BDBJPerfDistribution.pdf")

par(mfrow=c(3, 2))

colnames <- c("N", "2*N", "3*N", "M", "All", "X100")

for (i in 1:5) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(2950, 16550), breaks=seq(2950, 16550, 1360), main= colnames[i], ylab="Frequency", xlab="Performance", col="grey90", border=TRUE)
   rug(na.omit(dspvset[,i]))
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="red")
   
}

dev.off()

## LLVM

dspvset <- read.csv("../DataSet/PerfDistribution/LLVMPerfValues.csv", header = TRUE, sep = ",")

pdf("../DataSet/PerfDistribution/LLVMPerfDistribution.pdf")

par(mfrow=c(3, 2))

colnames <- c("N", "2*N", "3*N", "M", "All", "X100")

for (i in 1:5) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(198, 270), breaks=seq(198, 270, 8), main= colnames[i], ylab="Frequency", xlab="Performance", col="grey90", border=TRUE)
   rug(na.omit(dspvset[,i]))
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="red")
   
}

dev.off()

## X264

dspvset <- read.csv("../DataSet/PerfDistribution/X264PerfValues.csv", header = TRUE, sep = ",")

pdf("../DataSet/PerfDistribution/X264PerfDistribution.pdf")

par(mfrow=c(3, 2))

colnames <- c("N", "2*N", "3*N", "M", "All", "X100")

for (i in 1:5) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(244,824), breaks=seq(244,824, 58), main= colnames[i], ylab="Frequency", xlab="Performance", col="grey90", border=TRUE)
   rug(na.omit(dspvset[,i]))
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="red")
   
}

dev.off()

## SQL

dspvset <- read.csv("../DataSet/PerfDistribution/SQLPerfValues.csv", header = TRUE, sep = ",")

pdf("../DataSet/PerfDistribution/SQLPerfDistribution.pdf")

par(mfrow=c(3, 2))

colnames <- c("N", "2*N", "3*N", "M", "All", "X100")

for (i in 1:6) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(12.5, 17), breaks=seq(12.5, 17, 0.5), main= colnames[i], ylab="Frequency", xlab="Performance", col="grey90", border=TRUE)
   rug(na.omit(dspvset[,i]))
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="red")
   
}

dev.off()
