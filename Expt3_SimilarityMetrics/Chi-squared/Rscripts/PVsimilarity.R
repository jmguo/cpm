
# calculate the relative frequency

#	no = 1
#	sizenumber = c(9, 18, 27, 29, 192)
	
#	no = 2
#	sizenumber = c(18, 36, 54, 139, 2560)

#	no = 3
#	sizenumber = c(26, 52, 78, 48, 180)

#  no = 4
#  sizenumber = c(11, 22, 33, 62, 1024)

	 no = 5
	 sizenumber = c(39, 78, 117, 566, 4553)

#	 no = 6
#	 sizenumber = c(16, 32, 48, 81, 1152)

	
	# subject systems
	systems = c('Apache', 'BDBC', 'BDBJ', 'LLVM', 'SQL', 'X264')
	fnumbers = c(9, 18, 26, 11, 39, 16)
	
  # sample size
	sizes = c('N1', 'N2', 'N3', 'PW', 'All')
	
	# random generation
	generations = c('R1', 'R2', 'R3', 'R4', 'R5')
  
	# get the whole population
	path=paste("../DataSource/", systems[no], "/All.csv", sep = "")
	dsAll <- read.csv(path, header = TRUE, sep = ",")
	
	# The dataframe of relative frequency for each system
	dvalues = unique(dsAll["Performance"])
	freqAll=0
	freqN1=0
	freqN2=0
	freqN3=0
	freqPW=0
	relfreq <- data.frame(dvalues, freqAll, freqN1, freqN2, freqN3, freqPW)
	
	# for the whole population
	for (row1 in 1:nrow(dsAll)){
					for (row2 in 1:nrow(relfreq)){	
			    	if( dsAll[row1,"Performance"] == relfreq[row2, "Performance"])
			    		relfreq[row2, "freqAll"] = relfreq[row2, "freqAll"] + 1
			    }
	}
	relfreq[,"freqAll"]=relfreq[,"freqAll"]/sizenumber[5]
	
  # for samples
	for (i in 1:4){
		for (j in 1:5){
			path=paste("../DataSource/", systems[no], "/", sizes[i], generations[j], "training.csv", sep = "")
			ds <- read.csv(path, header = TRUE, sep = ",")
			  for (row1 in 1:nrow(ds)){
			    for (row2 in 1:nrow(relfreq)){
			    	if( ds[row1,"Performance"] == relfreq[row2, "Performance"] ){
			    		relfreq[row2, paste("freq", sizes[i], sep="")]=relfreq[row2, paste("freq", sizes[i], sep="")]+1
			    	}
			    }
			  }
		}
		relfreq[, paste("freq", sizes[i], sep="")]=relfreq[, paste("freq", sizes[i], sep="")]/sizenumber[i]/length(generations)
	} 
				  
	print(relfreq)
	
	outputpath1= paste("../Results/PerformanceValue/", systems[no], "/", "relfreq.csv", sep = "")
	write.csv(relfreq, file = outputpath1)
	
# calculate the similarity between each sample and the whole population

	similarity <- c(0, 0, 0, 0)
	for (i in 1:4){
		for (j in 1:nrow(relfreq)){
			similarity[i]=similarity[i] + (relfreq[j,paste("freq", sizes[i], sep="")]-relfreq[j,"freqAll"])^2/relfreq[j,"freqAll"]
		}
		similarity[i]=similarity[i]/dim(dvalues)[1]
	}
	
	print(similarity)
	
	outputpath2= paste("../Results/PerformanceValue/", systems[no], "/", "similarity.csv", sep = "")
	write.csv(similarity, file = outputpath2)
	