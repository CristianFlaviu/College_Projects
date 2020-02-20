package model;

public class Client {
	private String name;
	private int age;
	private String adress;
	private String email;
	private int idClient;

	
	
	public Client(int clientId,String name,String adress,String email,int age)
	{
		this.idClient=clientId;
		this.name=name;
		this.adress=adress;
		this.age=age;
		this.email=email;
	}
	public Client() {
		this.idClient=8;
		this.name="abc ";
		this.adress="cawsn";
		this.age=5;
		this.email="nt";
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString()
	{
		return getName()+" "+getAge()+" "+getAdress()+" "+getEmail()+" "+getIdClient();
	}
	public String[] toArrayString()
	{	String[] aString=new String[5];
	
		aString[1]=getName();
		aString[2]=getAge()+"";
		aString[3]=getAdress();
		aString[4]=getEmail();
		aString[0]=getIdClient()+"";
		
		return aString;
		
	}
	
}
