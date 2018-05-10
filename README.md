# Parser
Small class that implements a given grammar


 The follwoing grammar is implemented
 
 
 P → S ;  
 
 S → CS'				
 
 S' → *CS' | ε		
 
 C → OC'					 
 
 C' → q O' C' | n O' C' | [ S ]  O' C' |ε	 
 
 O → AO'               
 
 O' →  ! O' | = n O'			
 
 A → q | n | [ S ] 		
 
