/*
 * Finds all genes present in any DNA sequence
 */

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class ProcessDnaGenes {
	
	//Returns the index of the given stopCodon after the startCodon
	//Returns positive infinity if stopCodon is not found
	public int findStopCodon(String dna, int startIndex, String stopCodon){
		int currIndex = dna.indexOf(stopCodon, startIndex+3);
		while(currIndex != -1){
			if((currIndex-startIndex)%3 == 0)
				return currIndex;
			else
				currIndex = dna.indexOf(stopCodon, currIndex+1);
		}
		//If stopCodon is not found, return Integer.MAX_VALUE
		return Integer.MAX_VALUE;
	}
	
	//Returns the gene string, if present in the given dna string
	public String getGene(String dna, int startFrom){
			
		int startIndex = dna.indexOf("ATG", startFrom);
		if(startIndex == -1)
			return "";
		
		//Searches for any occurrence of either of the three stop codons after startcodon
		int indexTAA = findStopCodon(dna, startIndex, "TAA");
		int indexTGA = findStopCodon(dna, startIndex, "TGA");
		int indexTAG = findStopCodon(dna, startIndex, "TAG");
		
		//Keep the first stop codon that occurs after the first codon
		int minIndex = Math.min(indexTAA, Math.min(indexTGA, indexTAG));
		
		//If no stop codon is present, there is no gene, return empty string
		if(minIndex == Integer.MAX_VALUE)
			return "";
		else
			return dna.substring(startIndex, minIndex+3);
	}
	
	//Returns all genes in the given dna sequence
	public StorageResource findAllGenes(String dna){
		StorageResource sr = new StorageResource();
		int startIndex = 0;
		while(true){
			String currentGene = getGene(dna, startIndex);
			if( currentGene.isEmpty())
				break;
			else
				sr.add(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) +currentGene.length();
		}
		return sr;
	}
	
	
	
	//Returns the CG ratio 
	public double cgRatio(String dna){
		double ratio = 0;
		int startIndex = dna.indexOf("C");
		double countC = 0;
		double countG = 0;
		while(startIndex!=-1){
			countC++;
			startIndex = dna.indexOf("C", startIndex+1) ;
		}
		
		startIndex = dna.indexOf("G");
		while(startIndex!=-1){
			countG++;
			startIndex = dna.indexOf("G", startIndex+1) ;
		}
		
		ratio = (countC+countG)/dna.length();
		
		return ratio;
	}
	
	
	//Prints all the genes in the DNA sequence
	public void processGenes(StorageResource geneList){
		int countGene = 0;
		int countGeneHigherCG = 0;
		int longestGeneLen = 0;
		int totalGenes = 0;
		
		
		for(String gene : geneList.data()){
			totalGenes++;
		}
		System.out.println("Total number of Genes :"+totalGenes);		
		
		System.out.println();
		System.out.println("Genes whose length is greater than 9 :");
		for(String gene : geneList.data()){
			if(gene.length()>60){
				countGene++;
				System.out.println(gene);
			}
		}
		System.out.println("Number of genes with length greater than 60 :"+countGene);
		
		System.out.println();
		System.out.println("Genes whose C-G-ratio is higher than 0.35 :");
		for(String gene : geneList.data()){
			if(cgRatio(gene)>0.35){
				countGeneHigherCG++;
				System.out.println(gene);
			}
		}
		System.out.println("Number of Genes whose C-G-ratio is higher than 0.35 :"+countGeneHigherCG);
		
		System.out.println();
		for(String gene : geneList.data()){
			longestGeneLen = Math.max(longestGeneLen, gene.length());
		}
		System.out.println("Length of the longest gene :"+longestGeneLen);
		
		System.out.println();
		
	}
	
	//Returns the number of times codon CTG occurs in the dna
	public int ctgCount(String dna){
		int count = 0;
		int startIndex = dna.indexOf("CTG");
		while(startIndex!=-1){
			count++;
			startIndex = dna.indexOf("CTG", startIndex+3);
		}
		return count;
	}
	public static void main(String[] args) {
		ProcessDnaGenes fg = new ProcessDnaGenes();
		
		//Read the fasta file for the dna sequence 
		FileResource fr = new FileResource();
		String dna = fr.asString().toUpperCase();
		
		StorageResource geneList = fg.findAllGenes(dna);
		fg.processGenes(geneList);
		
		System.out.println("Number of times CTG occurs :"+fg.ctgCount(dna));
	}
}
