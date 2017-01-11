package reporting.framework.reporting;

import java.util.LinkedList;

public class Css {
	
	public enum Body
	{
		ClassStarter("\n\t\t\t body { \n\t\t\t\t"),
		BackgroundColor("background-color: #000000; \n\t\t\t\t"),
		FontFamily("font-family: sans-serif; \n\t\t\t\t"),
		Width("width: 98%; \n\t\t\t\t"),
		TextAlign("text-align: justify; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		
		public String value;
		Body(String body){
			this.value = body;
		}
		
	}
	
	public enum DivSubheading
	{
		ClassStarter("\n\t\t\t div.subheading { \n\t\t\t\t"),
		BackgroundColor("background-color: #FFFFFF; \n\t\t\t\t\t"),
		Color("color: #2b9cd8; \n\t\t\t\t\t"),
		FontWeight("font-weight: bold; \n\t\t\t\t"),
		FontSize("font-size: 1em; \n\t\t\t\t"),
		TextAlign("text-align: justify; \n\t\t\t\t"),
		Width("width: 98%; \n\t\t\t\t"),
		MarginLeft("margin-left: 19px; \n\t\t\t\t"),
		MarginRight("margin-right: -1px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		DivSubheading(String divSubHeading){
			this.value = divSubHeading;
		}
	}
	
	public enum DivSection
	{
		ClassStarter("\n\t\t\t div.section { \n\t\t\t\t"),
		BackgroundColor("background-color: #D6D8D8; \n\t\t\t\t"),
		Color("color: #000000; \n\t\t\t\t"),
		Cursor("cursor: pointer; \n\t\t\t\t"),
		FontWeight("font-weight: bold; \n\t\t\t\t"),
		FontSize("font-size: 0.9em; \n\t\t\t\t"),
		TextAlign("text-align: justify; \n\t\t\t\t"),
		Width("width: 100%; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		DivSection(String value){
			this.value = value;
		}
	}
	
	public enum DivSubSection
	{
		ClassStarter("\n\t\t\t div.section { \n\t\t\t\t"),
		Cursor("cursor: pointer; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		DivSubSection(String value){
			this.value = value;
		}
	}
	
	public enum TrContent
	{
		ClassStarter("\n\t\t\t tr.content { \n\t\t\t\t"),
		BackgroundColor("background-color: #FFFFFF; \n\t\t\t\t"),
		Color("color: #000000; \n\t\t\t\t"),
		FontSize("font-size: 1.2em; \n\t\t\t\t"),
		Display("display: table-row; \n\t\t\t\t"),
		Width("width: 100%; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		TrContent(String value){
			this.value = value;
		}
	}
	
	public enum H1
	{
		ClassStarter("\n\t\t\t h1 { \n\t\t\t\t"),
		FontSize("font-size: 50px; \n\t\t\t\t"),
		TextAlign("text-align: -webkit-center; \n\t\t\t\t"),
		BackgroundColor("background-color: #2b9cd8; \n\t\t\t\t"),
		Color("color: #FFFFFF; \n\t\t\t\t"),
		Padding("padding: 20px; \n\t\t\t\t"),
		MarginLeft("margin-left: 15px; \n\t\t\t\t"),
		FontWeight("font-weight: normal; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		H1(String value){
			this.value = value;
		}
	}
	
	public enum H2
	{
		ClassStarter("\n\t\t\t h2 { \n\t\t\t\t"),
		FontSize("font-size: 30px; \n\t\t\t\t"),
		TextAlign("text-align: -webkit-center; \n\t\t\t\t"),
		FontWeight("font-weight: lighter; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		H2(String value){
			this.value = value;
		}
	}
	
	public enum P
	{
		ClassStarter("\n\t\t\t p { \n\t\t\t\t"),
		MarginLeft("margin-left: 15px; \n\t\t\t\t"),
		BackgroundColor("background-color: #2b9cd8; \n\t\t\t\t"),
		Color("color: #FFFFFF; \n\t\t\t\t"),
		Padding("padding: 10px; \n\t\t\t\t"),
		MarginTop("margin-top: 10px; \n\t\t\t\t"),
		MarginBottom("margin-bottom: 10px; \n\t\t\t\t"),
		FontSize("font-size: 25px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		P(String value){
			this.value = value;
		}
	}
	
	public enum Table
	{
		ClassStarter("\n\t\t\t table { \n\t\t\t\t"),
		BackgroundColor("background-color: #FFFFFF; \n\t\t\t\t"),
		Color("color: #2b9cd8; \n\t\t\t\t"),
		MarginLeft("margin-left: 15px; \n\t\t\t\t"),
		Width("width:98.5% \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		Table(String value){
			this.value = value;
		}
	}
	
	public enum Th
	{
		ClassStarter("\n\t\t\t th { \n\t\t\t\t"),
		TextAlign("text-align: left; \n\t\t\t\t"),
		PaddingLeft("padding-left: 150px; \n\t\t\t\t"),
		PaddingRight("padding-right: 150px; \n\t\t\t\t"),
		PaddingTop("padding-top: 10px; \n\t\t\t\t"),
		PaddingBottom("padding-bottom: 10px; \n\t\t\t\t"),
		FontSize("font-size: 20px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		Th(String value){
			this.value = value;
		}
	}
	
	public enum Td
	{
		ClassStarter("\n\t\t\t td { \n\t\t\t\t"),
		PaddingRight("padding-right: 630px; \n\t\t\t\t"),
		FontSize("font-size: 20px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		Td(String value){
			this.value = value;
		}
	}
	
	public enum ButtonAccordion
	{
		ClassStarter("\n\t\t\t button.accordion { \n\t\t\t\t"),
		BackgroundColor("background-color: #eee; \n\t\t\t\t"),
		Color("color: #444; \n\t\t\t\t"),
		Cursor("cursor: pointer; \n\t\t\t\t"),
		Padding("padding: 18px; \n\t\t\t\t"),
		Width("width: 96.5%; \n\t\t\t\t"),
		Border("border: none; \n\t\t\t\t"),
		TextAlign("text-align: left; \n\t\t\t\t"),
		Outline("outline: none; \n\t\t\t\t"),
		FontSize("font-size: 20px; \n\t\t\t\t"),
		Transition("transition: 1s; \n\t\t\t\t"),
		MarginLeft("margin-left: 30px; \n\t\t\t\t"),
		MarginBottom("margin-bottom: 10px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		ButtonAccordion(String value){
			this.value = value;
		}
	}
	
	public enum AccordionShow
	{
		ClassStarter("\n\t\t\t accordion.show { \n\t\t\t\t"),
		Display("display: block!important; \n\t\t\t\t"),
		Opacity("opacity: 1; \n\t\t\t\t"),
		MaxHeight("max-height: 500px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		AccordionShow(String value){
			this.value = value;
		}
	}
	
	public enum Panel
	{
		ClassStarter("\n\t\t\t .panel { \n\t\t\t\t"),
		Padding("padding: 0 18px; \n\t\t\t\t"),
		BackgroundColor("background-color: white; \n\t\t\t\t"),
		MaxHeight("max-height: 0; \n\t\t\t\t"),
		OverFlow("overflow: hidden; \n\t\t\t\t"),
		Transition("transition: 0.6s ease-in-out; \n\t\t\t\t"),
		Opacity("opacity: 0; \n\t\t\t\t"),
		Width("width: 96.5%; \n\t\t\t\t"),
		MarginLeft("margin-left: 30px; \n\t\t\t\t"),
		BorderRadius("border-radius: 0px; \n\t\t\t\t"),
		MarginBottom("margin-bottom: 0px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		Panel(String value){
			this.value = value;
		}
	}
	
	public enum PanelShow
	{
		ClassStarter("\n\t\t\t .show { \n\t\t\t\t"),
		Display("display: block!important; \n\t\t\t\t"),
		Opacity("opacity: 1; \n\t\t\t\t"),
		MaxHeight("max-height: 500px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		PanelShow(String value){
			this.value = value;
		}
	}
	
	public enum H3
	{
		ClassStarter("\n\t\t\t h3 { \n\t\t\t\t"),
		FontSize("font-size: 18px; \n\t\t\t\t"),
		MarginTop("margin-top: 20px; \n\t\t\t\t"),
		MarginBottom("margin-bottom: 10px; \n\t\t\t\t"),
		PaddingBottom("padding-bottom: 20px; \n\t\t\t"),
		ClassEnder("} \n\t\t\t");
		public String value;
		H3(String value){
			this.value = value;
		}
	}
	
	public enum BtnAccordionActive
	{
		ClassStarter("\n\t\t\t button.accordion.active { \n\t\t\t\t"),
		BackgroundColor("background-color: #ababab; \n\t\t\t\t"),
		MarginBottom("margin-bottom: 0px; \n\t\t\t"),
		ClassEnder("} \n\t\t");
		public String value;
		BtnAccordionActive(String value){
			this.value = value;
		}
	}
	
	public enum BtnHover
	{
		ClassStarter("\n\t\t\t button.accordion:hover { \n\t\t\t\t"),
		BackgroundColor("background-color: #ababab; \n\t\t\t\t"),
		MarginBottom("margin-bottom: 0px; \n\t\t\t"),
		ClassEnder("} \n\t\t");
		public String value;
		BtnHover(String value){
			this.value = value;
		}
	}
	
	public static LinkedList<String> getBody() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (Body s : Body.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getDivSubHeading() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (DivSubheading s : DivSubheading.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getDivSection() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (DivSection s : DivSection.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getDivSubSection() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (DivSubSection s : DivSubSection.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getTrContent() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (TrContent s : TrContent.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getH1() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (H1 s : H1.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getH2() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (H2 s : H2.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getP() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (P s : P.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getTable() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (Table s : Table.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getTh() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (Th s : Th.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getTd() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (Td s : Td.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getButtonAccordion() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (ButtonAccordion s : ButtonAccordion.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getAccordionShow() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (AccordionShow s : AccordionShow.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getPanel() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (Panel s : Panel.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getPanelShow() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (PanelShow s : PanelShow.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getH3() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (H3 s : H3.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getBtnAccordionActive() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (BtnAccordionActive s : BtnAccordionActive.values()) {
	        list.add(s.value);
	    }

	    return list;
	}
	
	public static LinkedList<String> getBtnHover() {

	    java.util.LinkedList<String> list = new LinkedList<String>();
	    for (BtnHover s : BtnHover.values()) {
	        list.add(s.value);
	    }

	    return list;
	}

}
