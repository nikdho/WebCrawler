package algorithms;
import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.filter.Filter;
import org.jdom2.filter.Filters;
import org.jdom2.input.*;
import org.jdom2.util.IteratorIterable;

public class WebCrawler {
	public static Graph<String> webCrawl(String[] filenames) throws JDOMException, IOException, FileNotFoundException {
		double[][] counts = new double[filenames.length][filenames.length];
		ArrayList<String> files = new ArrayList<String>();
		for(int i=0; i<filenames.length; i++) 
			files.add(filenames[i]);
		SAXBuilder sax = new SAXBuilder();
		Filter<Element> filter = (Filter<Element>) Filters.element("a");
		for(int i=0;i<files.size();i++) {
			FileInputStream inputFile = new FileInputStream(new File(files.get(i)));
			Document doc = (Document)sax.build(inputFile);
			IteratorIterable<Element> aFind = doc.getDescendants(filter);
			while(aFind.hasNext()) {
				Element e = aFind.next();
				String s = e.getAttributeValue("href");
				for(int j=0;j<files.size();j++)
					if(s.contains(files.get(j))) {
						counts[i][j]+=1.0;
					}
			}
		}
		//link display graph
		Graph<String> linkDisp = new Graph<String>();
		for( int i=0;i<files.size();i++) {
			GraphNode<String> links = new GraphNode<String>(files.get(i));
			linkDisp.addVertex(links);
		}
			
		for(int i=0;i<files.size();i++)
			for(int j=0;j<files.size();j++) {
				Double sum=counts[i][j] + counts[j][i];
				if(sum>0)
				linkDisp.addEdgeSym(linkDisp.getVertex(i), linkDisp.getVertex(j), sum);
			}
		return linkDisp;
	}

	public static void main(String []args) throws JDOMException, IOException, FileNotFoundException {
		System.out.println(webCrawl(args).toStringWithWeights());
	}
}
