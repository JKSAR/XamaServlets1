package json.xama1.service;

import json.xama1.controller.ClassSingleton;
import json.xama1.model.Xamada;
import java.util.Arrays;
import java.util.List;

public class XamaService {
	public ClassSingleton classSingleton;
	
	public List<Xamada> getXamadas()
	{
		classSingleton = ClassSingleton.getInstance();
		return Arrays.asList(new Xamada(1, classSingleton.getMesaNum()));
	}
	
	public Xamada getXamada()
	{
		classSingleton = ClassSingleton.getInstance();
		return(new Xamada(1, classSingleton.getMesaNum()));
	}
}
