## Family Tree
Given family and extended family information, I was asked to construct a family tree from that information and need to find the list of eligible people one can marry. The conditions were as follows:

For a male, a match will be his father’s sister’s daughter or his mother’s brothers’ daughter.
For a female, a match will be her father’s sister’s son or her mother’s brother’s son.
Names are unique.

Input format:\
<person's name>, <gender>, <father's name>, <mother's name>

Input:\
John, Male, Brad, Lisa\
Emma, Female, Brad, Lisa\
Alex, Male, John, Jenny\
Emily, Female, Steve, Emma\
Rachel, Female, Steve, Emma

Person name: Alex\
Output: Emily, Rachel
