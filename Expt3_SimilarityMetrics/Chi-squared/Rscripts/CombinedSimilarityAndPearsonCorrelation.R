
# calculate the relative frequency

#	no = 1
#	sizenumber = c(9, 18, 27, 29, 192)
#	faultrate = c(26.9, 11.6, 8.4, 9.7)
	
#	no = 2
#	sizenumber = c(18, 36, 54, 139, 2560)
#	faultrate = c(112.4, 98.3, 46.8, 7.8)

#	no = 3
#	sizenumber = c(26, 52, 78, 48, 180)
#	faultrate = c(3.2, 2.2, 2.6, 2.7)

#  no = 4
#  sizenumber = c(11, 22, 33, 62, 1024)
#  faultrate = c(5.7, 4.5, 4.0, 3.3)

#	 no = 5
#	 sizenumber = c(39, 78, 117, 566, 4553)
#	 faultrate = c(8.0, 8.1, 7.6, 7.2)

	 no = 6
	 sizenumber = c(16, 32, 48, 81, 1152)
	 faultrate = c(15.1, 8.5, 7.2, 6.4)

	
	# subject systems
	systems = c('Apache', 'BDBC', 'BDBJ', 'LLVM', 'SQL', 'X264')
	fnumbers = c(9, 18, 26, 11, 39, 16)
	
  # sample size
	sizes = c('N1', 'N2', 'N3', 'PW', 'All')
	
	# random generation
	generations = c('R1', 'R2', 'R3', 'R4', 'R5')
  
	# get the feature selection similarity
	path=paste("../Results/FeatureSelection/", systems[no], "/similarity.csv", sep = "")
	fssim <- read.csv(path, header = TRUE, sep = ",")
	
	# get the performance value similarity
	path=paste("../Results/PerformanceValue/", systems[no], "/similarity.csv", sep = "")
	pvsim <- read.csv(path, header = TRUE, sep = ",")
	
	# combine the two similarities, treat them equally
	sim <- c(0, 0, 0, 0)
	for (i in 1:4){
			sim[i]=( fssim[i,"x"] + pvsim[i,"x"] )/2
	}
	
	print(sim)
	
	outputpath1= paste("../Results/CombinedSimilarity/", systems[no], "CombinedSim.csv", sep = "")
	write.csv(sim, file = outputpath1)
	
	# calculate the similarity
	corr <- cor(sim, faultrate, use="pairwise", method="pearson")
					  
	print(corr)
	
	outputpath2= paste("../Results/Correlation/", systems[no], "Correlation.csv", sep = "")
	write.csv(corr, file = outputpath2)
	