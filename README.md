# Coati
Amplicon Panel Generation

This program generates amplicon panels for you. You just put in a bed file with genes you care about tiling over (as well as a refernce FASTA), and it will do the rest.

You have to specify size of the tile (amplicon), any gap between tiles, and an offset (move the first tile position in/ out - with respect to the bed file start position.

You will probably want to submit separate .bed files for whole genes and SNP's. i.e. You probably want a gene that is tiled over to have a start index of the beginning of the gene (offset = 0), but you probably want a single tile generated for a SNP to appear somewhere in the middle of the amplicon (offset = -(.5 * tile size))

To make full use of this program, please use a reference fasta that has the introns soft-masked with lower-case letters.
Until further notice, the input bed file should have the format:
contig_name start_position  end_position  description_or_gene_name

Although other fields may appear, and gene names/descriptors are optional for bed files, this program expects them.

Things not tested:
- Offsets that might break the program.
- Tile lengths & gaps that might break the program.
- Hard masked characters like X/x's.

Dependencies: 
- Java 8 (1.8)
- Samtools: Any version with FAIDX that matches the 1.9 command input structure.
- Linux - The call to samtools is through a BASH script- that is generated ad-hoc for each region. This will not change until the tool gets some serious time investment for doing the things that FAIDX does.

Version 1.0 - Last Updated 2019-11-11
-Salvatore Facista
