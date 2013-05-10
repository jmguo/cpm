## Apache

dspvset <- read.csv("../ApachePerfValues.csv", header = TRUE, sep = ",")

for (j in 1:5){

 path=paste("../ApacheN",j,".pdf", sep = "")

 pdf(path)

 for (i in j:j) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(840, 2640), breaks=seq(840, 2640, 200), xaxt="n",yaxt="n", xlab=NULL, ylab=NULL,main=NULL, col="grey90", border=TRUE)
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="black")
   
 }

 dev.off()
}

#pdf("../DataSet/PerfDistribution/ApachePerfDistribution.pdf")

#par(mfrow=c(3, 2))

#colnames <- c("N", "2*N", "3*N", "M", "All", "X100")

#for (i in 1:5) {
   
#   hs <- hist(na.omit(dspvset[,i]), xlim=c(840, 2640), breaks=seq(840, 2640, 200), main= colnames[i], ylab="Frequency", xlab="Performance", col="grey90", border=TRUE)
#   rug(na.omit(dspvset[,i]))
#   dens <- density(na.omit(dspvset[,i]))
#   rs[i] <- max(hs$counts)/max(dens$y)
#   lines(dens$x, dens$y*rs[i], type="l", col="red")
#}

#dev.off()

## BDBC

dspvset <- read.csv("../BDBCPerfValues.csv", header = TRUE, sep = ",")

for (j in 1:5){

 path=paste("../BDBCN",j,".pdf", sep = "")

 pdf(path)

 for (i in j:j) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(0, 40), breaks=seq(0, 40, 4), xaxt="n",yaxt="n", xlab=NULL, ylab=NULL,main=NULL, col="grey90", border=TRUE)
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="black")
   
 }

 dev.off()
}


## BDBJ

dspvset <- read.csv("../BDBJPerfValues.csv", header = TRUE, sep = ",")

for (j in 1:5){

 path=paste("../BDBJN",j,".pdf", sep = "")

 pdf(path)

 for (i in j:j) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(2950, 16550), breaks=seq(2950, 16550, 1360), xaxt="n",yaxt="n", xlab=NULL, ylab=NULL,main=NULL, col="grey90", border=TRUE)
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="black")
   
 }

 dev.off()
}

## LLVM

dspvset <- read.csv("../LLVMPerfValues.csv", header = TRUE, sep = ",")

for (j in 1:5){

 path=paste("../LLVMN",j,".pdf", sep = "")

 pdf(path)

 for (i in j:j) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(198, 270), breaks=seq(198, 270, 8), xaxt="n",yaxt="n", xlab=NULL, ylab=NULL,main=NULL, col="grey90", border=TRUE)
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="black")
   
 }

 dev.off()
}

## X264

dspvset <- read.csv("../X264PerfValues.csv", header = TRUE, sep = ",")

for (j in 1:5){

 path=paste("../x264N",j,".pdf", sep = "")

 pdf(path)

 for (i in j:j) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(244,824), breaks=seq(244,824, 58), xaxt="n",yaxt="n", xlab=NULL, ylab=NULL,main=NULL, col="grey90", border=TRUE)
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="black")
   
 }

 dev.off()
}

## SQL

dspvset <- read.csv("../SQLPerfValues.csv", header = TRUE, sep = ",")

for (j in 1:5){

 path=paste("../SQLN",j,".pdf", sep = "")

 pdf(path)

 for (i in j:j) {
   
   hs <- hist(na.omit(dspvset[,i]), xlim=c(12.5, 17), breaks=seq(12.5, 17, 0.5), xaxt="n",yaxt="n", xlab=NULL, ylab=NULL,main=NULL, col="grey90", border=TRUE)
   dens <- density(na.omit(dspvset[,i]))
   rs <- max(hs$counts)/max(dens$y)
   lines(dens$x, dens$y*rs, type="l", col="black")
   
 }

 dev.off()
}
