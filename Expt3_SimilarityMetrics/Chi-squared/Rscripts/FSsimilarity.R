
#calculate the relative frequency

#	no = 1
#	sizenumber = c(9, 18, 27, 29, 192)
	
#	no = 2
#	sizenumber = c(18, 36, 54, 139, 2560)

#	no = 3
#	sizenumber = c(26, 52, 78, 48, 180)

#  no = 4
#  sizenumber = c(11, 22, 33, 62, 1024)

#	 no = 5
#	 sizenumber = c(39, 78, 117, 566, 4553)

	 no = 6
	 sizenumber = c(16, 32, 48, 81, 1152)

	
	# subject systems
	systems = c('Apache', 'BDBC', 'BDBJ', 'LLVM', 'SQL', 'X264')
	fnumbers = c(9, 18, 26, 11, 39, 16)
	
  # sample size
	sizes = c('N1', 'N2', 'N3', 'PW', 'All')
	
	# random generation
	generations = c('R1', 'R2', 'R3', 'R4', 'R5')
  
	# The matrix of relative frequency for each system
  relfreq = matrix(0, length(sizes), fnumbers[no])
  
  # for samples
	for (i in 1:4){
		for (j in 1:5){
			path=paste("../DataSource/", systems[no], "/", sizes[i], generations[j], "training.csv", sep = "")
			ds <- read.csv(path, header = TRUE, sep = ",")
			  for (row in 1:nrow(ds)){
			    for (col in 1:(ncol(ds)-1)){
			    	if(ds[row, col]=='Y'){
			    		relfreq[i, col]=relfreq[i, col]+1
			    	}
			    }
			  }
		}
		relfreq[i,]=relfreq[i,]/sizenumber[i]/length(generations)
	} 
	
	# for the whole population
	path=paste("../DataSource/", systems[no], "/All.csv", sep = "")
	ds <- read.csv(path, header = TRUE, sep = ",")
		for (row in 1:nrow(ds)){
			    for (col in 1:(ncol(ds)-1)){
			    	if(ds[row, col]=='Y'){
			    		relfreq[5, col]=relfreq[5, col]+1
			    	}
			    }
		}
	relfreq[5,]=relfreq[5,]/sizenumber[5]
			  
	print(relfreq)
	
	outputpath1= paste("../Results/FeatureSelection/", systems[no], "/", "relfreq.csv", sep = "")
	write.csv(relfreq, file = outputpath1)
	
# calculate the similarity between each sample and the whole population

	similarity <- c(0, 0, 0, 0)
	for (i in 1:4){
		for (j in 1:(ncol(ds)-1)){
			similarity[i]=similarity[i] + (relfreq[i,j]-relfreq[5,j])^2/relfreq[5,j]
		}
		similarity[i]=similarity[i]/fnumbers[no]
	}
	
	print(similarity)
	
	outputpath2= paste("../Results/FeatureSelection/", systems[no], "/", "similarity.csv", sep = "")
	write.csv(similarity, file = outputpath2)
	