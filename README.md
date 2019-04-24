# MDict Library in pure java ！
It supports:  
&nbsp;&nbsp;&nbsp;I.lzo(via lzo-core-1.0.5.jar)  
&nbsp;&nbsp;II.Ripemd128 key-info decryption.  
&nbsp;III.Builders to make Mdx add Mdd .  

and is able to do:  
&nbsp;&nbsp;&nbsp;I.Basic query.  
&nbsp;&nbsp;II.Conjuction search.  
&nbsp;III.Fast wildcard match entry.  
&nbsp;IV.Fast Fulltext retrieval (also with wild cards).  

# App Released!    
<a href="https://play.google.com/store/apps/details?id=com.knziha"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height="50px"/></a>  
![image](https://lh3.googleusercontent.com/gCP2nSx3V5MCksPy6NPj1MrFhCJm1GHfpMXm4lDkz3TUnNyJoJRGGdhy7Odec7nAfoyX=w1440-h620-rw)  

# Usage:
### 1.Basic query:
```
String key = "happy";
mdict md = new mdict(path);
int search_result = md.lookUp(key, true);//true means to match strictly  
if(search_result!=-1){
  String html_contents = md.getRecordAt(search_result);
  String entry_name = md.getEntryAt(search_result);
}
```
### 2.Search in a bunch of dicts:
```
String key = "happy";
ArrayList<mdict> mdxs = new ArrayList<mdict>();
mdxs.add(path1);
mdxs.add(path2);
RBTree_additive combining_search_tree = new RBTree_additive();
for(int i=0;i<mdxs.size();i++)
{
  mdxs.get(i).size_confined_lookUp(key,combining_search_tree,i,30);
}  	
combining_search_tree.inOrder();//print results stored in the RBTree

/*printed results looks like 【happy____@398825@0@16905@1@16906@1】...【other results】...
how to handle:
String 
html_contents0 = mdxs.get(0).getRecordAt(398825),
html_contents1 = mdxs.get(1).getRecordAt(16905),
html_contents2 = mdxs.get(1).getRecordAt(16906);
...  
...
*/
```

### 3. Other search methods
TODO See test/MdTest.java and the newly uploaded mdict-javafx demo! It's not yet complete though.
Updated: PlaneDictionary-javafx (using nanohttpd to serve a dictionary server and Javafx webview to show it. )
截图：
![image](https://github.com/KnIfER/tuChuang/raw/master/javaFx_PDPC_α.png)  


# details
* This project was initially converted from xiaoqiangWang's [python analyzer](https://bitbucket.org/xwang/mdict-analysis). 
* Use [red-black tree](http://www.cnblogs.com/skywang12345/p/3245399.html) and binary-list-searching(mainly) to implement dictionary funcitons.  
* Feng Dihai(@[fengdh](https://github.com/fengdh/mdict-js))'s mdict-js is of help too, I've just switched to use the same short but elegant binary-list-searching method——reduce().Somehow, this function always returns the first occurence of the entry >= keyword, in a pre-sorted list that contain entries. maybe some mathematician could tell me why, but I've tested over 100000 times without any expectation.
* Maybe I should oneday replace red-black tree and the recursive reduce method with that binarySearch method inside of AOSP, but I am lazy... 
```
/*via mdict-js
 *note at first time we feed in 0 as start and array.length as end. it must not be array.length-1. 
*/
public static int reduce(int phrase, int[] array,int start,int end) {
	int len = end-start;
	if (len > 1) {
	  len = len >> 1;
	  return phrase > array[start + len - 1]
				? reduce(phrase,array,start+len,end)
				: reduce(phrase,array,start,start+len);
	} else {
	  return start;
	}
}
```
	

<img src="https://github.com/KnIfER/mdict-parsr-java/raw/master/doc/MDX.svg">
