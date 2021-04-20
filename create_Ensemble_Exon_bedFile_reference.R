#Get the appropriate bed files from the table browser:
#Clade: Mammal genome:Dog Assembly:canfam3.1
#Group: genes and gene predictions track: Ensembl Genes
#table: ensGenes and EnsembltoGeneName

############################Libraries
library(dplyr)
############################

#############################Read in the files
transcriptBED = read.delim("C:/Users/sfacista/Desktop/canFam3.1_EnsemblGenes", header = TRUE, sep="\t")
transcriptNames = read.delim("C:/Users/sfacista/Desktop/canFam3.1_EnsemblNames", header = TRUE, sep="\t")

#############################Process the files
#Change the row name so we can join
transcriptNames$name =transcriptNames$X.name
#Replace with the new name
transcriptNames = subset(transcriptNames, select = c(name,value))

#Filter out anything that has "null start and end cds"none" cdsStart/EndStat
transcriptBED = subset(transcriptBED, transcriptBED$cdsStartStat != "none" )

#Perform the merge operation
mergedT = inner_join(transcriptBED, transcriptNames, by = "name")
mergedT$cdsTotal = with(mergedT,  cdsEnd - cdsStart)
msT = mergedT[order(mergedT$cdsTotal, decreasing = TRUE),]

#Create simplified bed file
simpleMST = subset(msT, select = c(chrom, cdsStart, cdsEnd, value))

#Keep only the first unique gene name (longest cds as sorted above)
dMST = distinct(simpleMST, value, .keep_all = TRUE)

#remove the row and column names prior to printing
colnames(dMST) = NULL
rownames(dMST) = NULL

#clean up
rm(transcriptBED, transcriptNames, mergedT, msT, simpleMST)

#Write the data for a .bed file 
write.table(dMST, file ="C:/Users/sfacista/Desktop/reference_Cannonical_Ensembl_genes_canine.bed", quote = FALSE, row.names = FALSE, col.names = FALSE,  sep ="\t")
