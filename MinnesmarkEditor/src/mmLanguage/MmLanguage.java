package mmLanguage;



public class MmLanguage {
	

	public static String language[][]=new String[][]{{"Dra och släpp stationer på kartan för att skapa en rundvandring","Klicka på en station för ett lägga till media","Sök efter en plats på kartan","Välj en markör och klicka 'Lägg till' för att koppla media till den","Lägg till media som spelas när rundvandringen startar","Lägg till media som spelas när hitta markören."},
		                                             {"        Drag and drop stations on the map to make a trail              ","         Click on a station to add media              ","Search for a location on the map","Choose a marker and click 'Add' to connect media to it","Add media that plays when a trail starts","Add media that plays when the marker is found."}}; 
	
	public static String language_menu[][]=new String[][]{{"Fil","Hjälp","Ny rundvandring N","Öppna rundvandring... O","Spara S","Spara Som...","Skriv ut karta och markörer P","Förhandsvisning av utskrift","Avsluta E","Handledningsfilmer","Om Minnesmark","Inställningar","Språk"},
		                                                  {"File","Help","New trail N","Open trail... O","Save S","Save As...","Print map and markers P","Print preview","Exit E","Tutorial videos","About Minnesmark","Settings","Languages"}};
	
	public static String language_markers[][]=new String[][]{{"Markör 1","Markör 2","Markör 3","Markör 4","Markör 5","Markör 6","Markör 7","Markör 8","Markör 9","Markör 10","Markör 11","Markör 12","Markör 13","Markör 14","Markör 15","Markör 16","Markör 17","Markör 18"},
		                                                     {"Marker 1","Marker 2","Marker 3","Marker 4","Marker 5","Marker 6","Marker 7","Marker 8","Marker 9","Marker 10","Marker 11","Marker 12","Marker 13","Marker 14","Marker 15","Marker 16","Marker 17","Marker 18"}};
	
	public static String language_exception[][]=new String[][]{{"Kan inte spara json-filen","Kan inte flytta stationen. Andra punkter är för nära","Kan inte flytta svängpunkten. Andra punkter är för nära","Kan inte lägga till en station. Andra punkter är för nära.","Vill du ta bort svängpunkten?","Vill du ta bort stationen?","Stationen"},
		                                                       {"Cannot save the json filen","Cannot move the station. Other points are too close","Cannot move the turning point. Other points are too close","Cannot add a station. Other points are too close.","Do you want to delete the turning point?","Do you want to delete the station?","Station"}};
		
	
	public static String language_jsonException[][] = new String[][]{{"Bilden hittades inte","Modellfilen hittades inte","Ljudfilen hittades inte","Videofilen hittades inte"},
		                                                             {"Image file not found","Model file not found","Sound file not found","Video file not found"}};
	
	public static String language_events[][] = new String[][]{{":skatt",":Modell",":Panorama","Lägg till media som spelas på stationen","Mediefiler"},
		                                                      {":treasure",":Model",":Panorama","Add media that plays at the station","Media files"}};
	
	public static String language_button[][] = new String[][]{{"Sök","Lägg till media","Ta bort media"},
		                                                      {"Search","Add media","Delete media"}};
	
	public static String language_media[][] = new String[][]{{"<html>Klicka på stationen för att lägga till mediefiler</html>"},
		                                                     {"<html>Click on the station to add media files</html>"}};
	
	public static String language_startMedia[][] = new String[][]{{"Lägg till en uppstartsbild","Lägg till media som spelas när rundvandringen startar"},
		                                                          {"Add a start image","Add media that plays when a trial starts"}};
	
	public static String language_mediaevents[][] = new String[][]{{"Panorama","Med kamerabakgrund","Mediafilen är en skatt","Stopp","Spela upp ljudet","Spela upp videon","Visa texten","skatt","Modell","modell","Visa bilden","Välj en bild"},
		                                                           {"Panorama","With camera background",/*"The media file is a treasure"*/"Mark media as treasure","Stop","Play the sound","Play the video","View the text","treasure","Model","model","View the image","Choose an image"}};
	
	public static String language_fileOptions[][] = new String[][]{{"Spara fil?","Spara","Filen finns redan. Vill du ersätta den?"},
		                                                           {"Save file?","Save","The file already exists. Do you want to replace it?"}}; 
	
	public static String language_mediaException[][] = new String[][]{{"Bildens bredd och höjd ska vara under 512 pixlar för att göra den som en 3D-modell.\n Men bildens bredd och höjd är ","Bilden hittades inte","Kan inte lägga till media, eftersome frösta media är 3D modell som binder till markören. Ta bort 3D mediafilen eller ta bort modell egenskap till lägga media.","Bildens bredd och höjd ska vara under 1024 pixlar för att göra den som en Panorama.\n Men bildens bredd och höjd är "},
		                                                              {"The image width and height should be less than 512 to make it a 3D model.\n But the image width and height is ","The image was not found","Cannot add media, because the first event is a 3D model binded to the marker. Remove 3D file or remove model property to add media.","The image width and height should be less than 1024 to make it a Panorama.\n But the image width and height is "}};
	
	public static String language_printException[][] = new String[][]{{"Det finns inga stationer eller markörer att skriva ut"},
		                                                              {"There are no stations or markers to print"}};
	
	public static String language_menu_languages[][] = new String[][]{{"Svenska","Engelska"},{"Swedish","English"}};
		                                                              
	
	public static String language_search[][] = new String[][]{{"Sök","Klicka på texten för att skriva in latituden","Klicka på texten för att skriva in longituden","Latitud","Longitud"},
		                                                      {"Search","Click the text to enter the latitude","Click the text to enter the longitude","Latitude","Longitude"}};
	
	public static String language_options[][] = new String[][]{{"Ja","Nej","Avbryt"},{"Yes","No","Cancel"}};
	
	public static String language_print[][]  = new String[][]{{"Markör","Media"},{"Marker","Media"}};
	
}
