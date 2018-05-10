# Parser
Small class that implements a given grammar


 
 GRAMMAR
 
 P → S ;  
 
 S → CS'				
 
 S' → *CS' | ε		
 
 C → OC'					 
 
 C' → q O' C' | n O' C' | [ S ]  O' C' |ε	 
 
 O → AO'               
 
 O' →  ! O' | = n O'			
 
 A → q | n | [ S ] 		
 
