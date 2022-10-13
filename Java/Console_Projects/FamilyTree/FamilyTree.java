import java.util.*;

class FamilyDetails {

    String child;
    String childGender, father, mother;

    FamilyDetails(String child, String childGender, String father, String mother) {
        this.child = child;
        this.childGender = childGender;
        this.father = father;
        this.mother = mother;
    }
    void printFamily(){
        System.out.println(" "+this.child+" "+this.childGender+" "+this.father+" "+this.mother+" ");
    }
}

class FamilyTree {
    static ArrayList<FamilyDetails> familyList = new ArrayList<FamilyDetails>();
    static String personName;
    static void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Input Details");
        boolean exit = false;
        do {
            System.out.println("Press 0 to Exit");
            String input = sc.nextLine().trim();
            if (input.equals("0")) {
                exit = true;
            } else {
                String[] familyData = input.split(",", 0);
                familyList.add(new FamilyDetails(familyData[0].trim(),familyData[1].trim(),familyData[2].trim(),familyData[3].trim()));
            }
        } while (!exit);
        System.out.println("\nEnter Person's Name : ");
        personName=sc.nextLine().trim();
    }

    static FamilyDetails searchPerson(){
        for(FamilyDetails fd : familyList){
            if(fd.child.equals(personName))
                return fd;
        }
        return familyList.get(0);
    }
    static ArrayList<String> checkEligiblity(FamilyDetails fd){
        String gender=fd.childGender;
        String[] fatherFamily=new String[2];
        String[] motherFamily=new String[2];

        ArrayList<String> fatherSister=new ArrayList<String>();
        ArrayList<String> motherBrother=new ArrayList<String>();
        
        ArrayList<FamilyDetails> relatives = new ArrayList<FamilyDetails>();
        ArrayList<String> eligilbeToMarry = new ArrayList<String>();

        int found=0;

        for(FamilyDetails family:familyList){
            if(family.child.equalsIgnoreCase(fd.father)){
                fatherFamily[0]=family.father;
                fatherFamily[1]=family.mother;
                found++;
            }
            
            if(family.child.equalsIgnoreCase(fd.mother)){
                motherFamily[0]=family.father;
                motherFamily[1]=family.mother;
                found++;
            }
            if(found==2)
                break;    
        }   

        for(FamilyDetails family:familyList){
            if(family.father.equalsIgnoreCase(fatherFamily[0]) && family.mother.equalsIgnoreCase(fatherFamily[1]) && family.childGender.equalsIgnoreCase("female") && family.child!=fd.father)
                fatherSister.add(family.child);
            if(family.father.equalsIgnoreCase(motherFamily[0]) && family.mother.equalsIgnoreCase(motherFamily[1]) && family.childGender.equalsIgnoreCase("male") && family.child!=fd.mother)
                motherBrother.add(family.child);
        }

        for(FamilyDetails family:familyList){
            for(String sister:fatherSister){
                if(family.mother.equalsIgnoreCase(sister)){
                    if(gender.equalsIgnoreCase("male") && family.childGender.equalsIgnoreCase("female"))
                        eligilbeToMarry.add(family.child);
                    if(gender.equalsIgnoreCase("female") && family.childGender.equalsIgnoreCase("male"))
                        eligilbeToMarry.add(family.child);
                }
            } 
            for(String brother:motherBrother){
                if(family.father.equalsIgnoreCase(brother)){
                    if(gender.equalsIgnoreCase("male") && family.childGender.equalsIgnoreCase("female"))
                        eligilbeToMarry.add(family.child);
                    if(gender.equalsIgnoreCase("female") && family.childGender.equalsIgnoreCase("male"))
                        eligilbeToMarry.add(family.child);
                }
            } 
        }
        return eligilbeToMarry;
    }
    public static void main(String[] args) {

        getInput();
        FamilyDetails familyDetail=searchPerson(); 
        ArrayList<String> eligibleToMarry=checkEligiblity(familyDetail);
        System.out.println("\nEligible To Marry : ");
        for(String person : eligibleToMarry)
            System.out.println(person);
    }

}