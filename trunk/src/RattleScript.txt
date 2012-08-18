# Rattle is Copyright (c) 2006-2012 Togaware Pty Ltd.

#============================================================
# Rattle timestamp: 2012-08-18 03:39:10 x86_64-pc-mingw32 

# Rattle version 2.6.20 user 'James'

# Export this log textview to a file using the Export button or the Tools 
# menu to save a log of all activity. This facilitates repeatability. Exporting 
# to file 'myrf01.R', for example, allows us to the type in the R Console 
# the command source('myrf01.R') to repeat the process automatically. 
# Generally, we may want to edit the file to suit our needs. We can also directly 
# edit this current log textview to record additional information before exporting. 
 
# Saving and loading projects also retains this log.

library(rattle)

# This log generally records the process of building a model. However, with very 
# little effort the log can be used to score a new dataset. The logical variable 
# 'building' is used to toggle between generating transformations, as when building 
# a model, and simply using the transformations, as when scoring a dataset.

building <- TRUE
scoring  <- ! building

# The colorspace package is used to generate the colours used in plots, if available.

library(colorspace)

# A pre-defined value is used to reset the random seed so that results are repeatable.

crv$seed <- 42 

#============================================================
# Rattle timestamp: 2012-08-18 03:40:46 x86_64-pc-mingw32 

# Load the data.

crs$dataset <- read.csv("file:///D:/Workspaces/eclipse-juno/workspace/ConfPerfModel/dataset/AccuracyTime/setNumberCP0/BDBC/BDBC139R1training.csv", na.strings=c(".", "NA", "", "?"), strip.white=TRUE, encoding="UTF-8")

#============================================================
# Rattle timestamp: 2012-08-18 03:40:48 x86_64-pc-mingw32 

# Note the user selections. 

# Build the training/validate/test datasets.

set.seed(crv$seed) 
crs$nobs <- nrow(crs$dataset) # 139 observations 
crs$sample <- crs$train <- sample(nrow(crs$dataset), 0.7*crs$nobs) # 97 observations
crs$validate <- sample(setdiff(seq_len(nrow(crs$dataset)), crs$train), 0.14*crs$nobs) # 20 observations
crs$test <- setdiff(setdiff(seq_len(nrow(crs$dataset)), crs$train), crs$validate) # 22 observations

# The following variable selections have been noted.

crs$input <- c("HAVE_HASH", "HAVE_REPLICATION", "HAVE_VERIFY", "HAVE_SEQUENCE",
     "HAVE_STATISTICS", "DIAGNOSTIC", "PS1K", "PS4K",
     "PS8K", "PS16K", "PS32K", "CS32MB",
     "CS16MB", "CS64MB", "CS512MB", "Performance")

crs$numeric <- "Performance"

crs$categoric <- c("HAVE_HASH", "HAVE_REPLICATION", "HAVE_VERIFY", "HAVE_SEQUENCE",
     "HAVE_STATISTICS", "DIAGNOSTIC", "PS1K", "PS4K",
     "PS8K", "PS16K", "PS32K", "CS32MB",
     "CS16MB", "CS64MB", "CS512MB")

crs$target  <- "HAVE_CRYPTO"
crs$risk    <- NULL
crs$ident   <- NULL
crs$ignore  <- c("PAGESIZE", "CACHESIZE")
crs$weights <- NULL

#============================================================
# Rattle timestamp: 2012-08-18 03:42:07 x86_64-pc-mingw32 

# Note the user selections. 

# The following variable selections have been noted.

crs$input <- c("HAVE_CRYPTO", "HAVE_HASH", "HAVE_REPLICATION", "HAVE_VERIFY",
     "HAVE_SEQUENCE", "HAVE_STATISTICS", "DIAGNOSTIC", "PS1K",
     "PS4K", "PS8K", "PS16K", "PS32K",
     "CS32MB", "CS16MB", "CS64MB", "CS512MB")

crs$numeric <- NULL

crs$categoric <- c("HAVE_CRYPTO", "HAVE_HASH", "HAVE_REPLICATION", "HAVE_VERIFY",
     "HAVE_SEQUENCE", "HAVE_STATISTICS", "DIAGNOSTIC", "PS1K",
     "PS4K", "PS8K", "PS16K", "PS32K",
     "CS32MB", "CS16MB", "CS64MB", "CS512MB")

crs$target  <- "Performance"
crs$risk    <- NULL
crs$ident   <- NULL
crs$ignore  <- c("PAGESIZE", "CACHESIZE")
crs$weights <- NULL

#============================================================
# Rattle timestamp: 2012-08-18 03:42:19 x86_64-pc-mingw32 

# Decision Tree 

# The 'rpart' package provides the 'rpart' function.

require(rpart, quietly=TRUE)

# Reset the random number seed to obtain the same results each time.

set.seed(crv$seed)

# Build the Decision Tree model.

crs$rpart <- rpart(Performance ~ .,
    data=crs$dataset[, c(crs$input, crs$target)],
    method="anova",
    parms=list(split="information"),
      control=rpart.control(minsplit=14,
           cp=0.000010,
        usesurrogate=0, 
        maxsurrogate=0))

# Generate a textual view of the Decision Tree model.

rattle.print.rpart(crs$rpart)
printcp(crs$rpart)
cat("\n")

# Time taken: 0.10 secs

# List the rules from the tree using a Rattle support function.

asRules(crs$rpart)