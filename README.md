# Trick2MonarcApi

## Introduction

Open-source Java API for MONARC (Optimised Risk Analysis Method) allowing to inject risk information from other sophisticated risk management tool such as TRICK (Tool for Riskmanagement of an ISMS based on a Central Knowledge base) by facilitating changes to the MONARC json data file. The tool was developed to migrate risk information of multiple organization in scope of NIS to the data format requested by the NIS regulator in Luxembourg.

This project is compliant with MONARC version [2.21.7](https://www.monarc.lu/news/2023/10/25/monarc-2127-released/). This API reads a JSON file exported from MONARC and gathers information by interepreting a subset of such file and creating Java objects from the elements it can interpret from the exported JSON file.

Furthermore, after the Java objects have been prcessed by this API, it can export a JSON file compliant with MONARC version 2.21.7.

# API usage

## Initializing the API

To begin interpreting the JSON file, instantiate a ```MonarcDatabase``` object and pass to the constructor the path to the Monarc Json file.

The newly instantiated MonarcDatabase object will then, parse the JSON file in a series of lists, each of which represent Monarc elements and their attributes.

A typical instance of MonarcDatabase contains the information encoded in the following Java Objects:

```BASH
MonarcDatabase
    |
    |-monarcData
    |-monarcRiskList
    |-monarcVulnerabilityList
    |-monarcThreatList
    |-monarcAMVThreatList
    |-monarcAMVList
    |-scales
    |-scalesComments
    |-soaScaleComment
    |-monarcObjectsList
    |-monarcThemesList
    |-monarcRecsList
    |-monarcRecosList
    |-monarcRecSetsList
    |-monarcReferentials
    |-monarcMeasures
    |-monarcSoaCategories
    |-monarcSoa
    |-monarcOperationalRiskScales
    |-monarcAssetList
    +-monarcANR
```

During initialization, each of these objects will be populated with the information coming from the elements in the Monarc JSON file, with the only difference being that in the JSON representation, information have to be replicated in order to appear in more than one element and in this data structure, objects just maintain a reference to unique objects, guaranteeing data consistency during the object lifecycle.

## Instantiating the MonarcDatabase object

The constructor of the MonarcDatabase class requires the path to a Monarc JSON file, and the instantiation should look like this:

```Java
MonarcDatabase db = new MonarcDatabase(<JSON filepath>);
```

After the initialization is ready, the database object is ready to process queries, such as the ones shown below:

```Java
MonarcThreats searchResult = db.searchThreatByLabel(<Threat label>, <language code>);
MonarcVulnerabilities vulnerabilitySearchResult = db.searchVulnerabilityByCode(<Code string>);
```

Finally, the MonarcDatabase project can be exported as a JSON file for transmitting to other Monarc users.

```Java
 db.saveInstancesToJSON(<Export filepath>);
```

## Querying the MonarcDatabase object for information

This API packs a set of functions to query the database object, as shown below:

### Searching by ID

The MonarcDatabase object allows the search to be conducted using only the UUID of a desired object. 

```Java
public MonarcRisks searchRiskByID(int riskID)
public MonarcThreats searchThreatByUUID(String uuid)
public MonarcAMV searchAMVByUUID(String uuid)
public MonarcVulnerabilities searchVulnerabilityByUUID(String uuid)
public MonarcReferentials searchReferentialsByUUID(String uuid)
public MonarcMeasures searchMeasuresByUUID(String uuid)

```

### Searching by code

Similar to searching by ID, but using the code field in the Monarc objects. Some of those functions are:

```Java
public MonarcThreats searchThreatByCode(String code)
public MonarcVulnerabilities searchVulnerabilityByCode(String code)
public List<MonarcMeasures> searchMeasuresByCode(String code)
```

### Searching by label

Some objects have labels associated with them, that could be written in more than one language.

One single object can have the same label written in different languages embedded in it. Usually encoded as follows:

| Language code | Language (typically) |
|---------------|----------------------|
|       1       |     French           |
|       2       |     English          |
|       3       |     German           |
|       4       |     Luxembourgish    |


Such functions are:

```Java
public List<MonarcInstance> searchInstanceByLabel(String label, int languageCode)
List<MonarcAsset> searchAssetByLabel(String label, int languageCode)
public MonarcThreats searchThreatByLabel(String label, int languageCode)
public MonarcVulnerabilities searchVulnerabilityByLabel(String label, int languageCode)
```

### Searching by description

Similar to searching by label, but the searched text is in the description field, not in the label field.

Functions that can be used to search by elements' descriptions are:

```Java
public MonarcThreats searchThreatByDescription(String description, int languageCode)
public MonarcVulnerabilities searchVulnerabilityByDescription(String description, int languageCode)
```

## License

Copyright © itrust consulting. All rights reserved.

Licensed under the GNU Affero General Public License (AGPL) v3.0.

## Contact

For more information about the project, contact us at dev@itrust.lu.
