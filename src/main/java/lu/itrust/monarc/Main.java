package lu.itrust.monarc;

public class Main {

    public static void main(String[] args) {
        try {
            MonarcDatabase db = new MonarcDatabase("src/main/resources/FileWithScaleComments.json");

            //Search Threat by label
            MonarcThreats searchResult = db.searchThreatByLabel("Equipment malfunction or failure", 2);
            
            //Search vulnerability by code
            //MonarcVulnerabilities vulnerabilitySearchResult = db.searchVulnerabilityByCode("ILR_NIS_VUL_009");

            //Get threats associated with vulnerability
            //List<MonarcThreats> vulnerabilities = db.getAllThreatsForVulnerability(vulnerabilitySearchResult);

            // db.UpdateThreatValue("563", "352");

            //db.searchConsequenceByAsset("CONT");
            //db.searchAssetByConsequence(5031);

            MonarcAsset newObjectAsset = new MonarcAsset("4740e624-2636-4a1b-a322-852a9ea84fda", 
            "Voiture", 
            "Car", 
            "", 
            "", 
            "A voiture noir", 
            "A black car", 
            "", 
            "", 
            0, 
            0, 
            0, 
            "VOIT", 
            null);

            MonarcObject newJsonObject = new MonarcObject("4740e624-2636-4a1b-a322-852a9ea84fda", 
                                                                0, 
                                                                0, 
                                                                "I am a new asset", 
                                                                "Je suis un asset nouveau", 
                                                                "", 
                                                                "", 
                                                                "A new asset created", 
                                                                "An nouveau asset creè", 
                                                                "", 
                                                                "", 
                                                                0, 
                                                                0, 
                                                                0, 
                                                                newObjectAsset);

            db.createNewAssetFile(newJsonObject, "src/main/resources/newAsset.json");

            // db.SaveMapToJson("src/main/resources/test-eom-2022-09-20-written.json");
            db.saveInstancesToJSON("target/classes/RicardoTest(4)_Finished.json");



            System.out.println(db.searchRiskByID(2508));
            

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
}
