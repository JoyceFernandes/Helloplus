package fr.ul.miage.helloplus;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Logger;
import javax.swing.text.html.Option;;

public class App {
	private static final Logger LOG=Logger.getLogger(App.class.getName());
	//attribus
	private String filename;
	//constructeurs
	public App(String filename){
		setFilename(filename);
	}
	//setters & getters
	public String getFilename{
		return filename;
	}
	public void setFilename(String filename){
		this.filename=filename;
	}
	
	public CSVParser buildCSVParser() throws IOException{
		CSVParser res=null:
			Reader in;
		in=new FileReader(filename);
		CSVFormat csvf=CSVFormat.DEFAULT.withCommentMarker('#').withDelimiter(';');
		res=new CSVParser(in, csvf);
		return res;
	}
	
	public static void main(String){
		//parametres
		String filename=null;
		//options
		Option option=new Option();
		Option input=new Option("i","input",true,"nom du ficher .csv contenant la liste des données");
		input.setRquired(true);
		option.addOption(input);
		//parse la ligne de commande
		CommandLineParser parser=new DelfaultParser();
		try{
			CommandLine line=parser.parse(option, args);
			if(line.hasOption("i")){
				filename)line.getOptionValue("i");
			}
		}
		catch(ParseException exp){
			LOG.severe("Erreur dans la ligne de commade");
			HelpFormatter formatter=new HelpFormatter();
			fromatter.prinHelp("App",option);
			System.exit(1);
		}
		
		App app=new App(filename);
		try{
			CSVParser p=app.buildCSVParser();
			for(CSVRecord r:p){
				String nom=r.get(0);
				String prenom=r.get(1);
				System.out.println("Hello "+nom+" "+prenom+" !");
			}
		}
		catch (IOException e){
			LOG.severe("Erreur de lecture dans le ficher CSV");
		}
	}
	
}
