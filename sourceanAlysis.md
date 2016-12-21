#集合类源码笔记
@()[Map,]

[toc]

##Map
Map是`java.util`下的接口简单解释如下：
&emsp;&emsp;An object that maps keys to values.  A map cannot contain duplicate keys; each key can map to at most one value.可以看到Map中的key是不能重复的，并且key value是一一对应关系。
&emsp;&emsp;同时在Map这个接口中定义了一个Entry的接口，key value的键值对实际上是保存在Entry中的。
###AbstractMap
所有Map都会继承这个抽象方法，AbstractMap实现了几个比较重要的方法：
##### Boolean containsValue(Object value):
判断map中是否包含了该value。

	public boolean containsValue(Object value) {
	        Iterator<Entry<K,V>> i = entrySet().iterator();
	        if (value==null) {
	            while (i.hasNext()) {
	                Entry<K,V> e = i.next();
	                if (e.getValue()==null)
	                    return true;
	            }
	        } else {
	            while (i.hasNext()) {
	                Entry<K,V> e = i.next();
	                if (value.equals(e.getValue()))
	                    return true;
	            }
	        }
	        return false;
	    }
代码实现了Iterator方式遍历entrySet（具体实现是在子类实现中，例如在HashMap中实现），然后通过判断遍历到的Entry的value是否等于传进来的value。（ 这边可以看到Map允许value的值为null）。
##### V get(Object key):
根据key查询所保存的value

	 public V get(Object key) {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    return e.getValue();
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    return e.getValue();
            }
        }
        return null;
    }
和上面contains方法非常类似，同样也是Iterator方式进行遍历，由代码可以看到在AbstractMap中Key值是可以有一个为null的，而HashTable后面是将该方法和部分方法重写了，使之不能将key设置为null.
(补充一句，看到api是这么遍历map的，可以借鉴一下，这种应该是效率最高的遍历方式，因此自己写代码进行map遍历的时候也可以选择使用这种方式进行遍历。)
##### V remove(Object key):
	public V remove(Object key) {
	        Iterator<Entry<K,V>> i = entrySet().iterator();
	        Entry<K,V> correctEntry = null;
	        if (key==null) {
	            while (correctEntry==null && i.hasNext()) {
	                Entry<K,V> e = i.next();
	                if (e.getKey()==null)
	                    correctEntry = e;
	            }
	        } else {
	            while (correctEntry==null && i.hasNext()) {
	                Entry<K,V> e = i.next();
	                if (key.equals(e.getKey()))
	                    correctEntry = e;
	            }
	        }
	
	        V oldValue = null;
	        if (correctEntry !=null) {
	            oldValue = correctEntry.getValue();
	            i.remove();
	        }
	        return oldValue;
	    }
要理解这段代码的前提是知道在Iterator中是可以`一边遍历一边删除`被遍历的容器的，这时foreach做不到的，也是因为这一点能够在循环中将对象从map中remove掉。
##### void putAll(Map<? extends K, ? extends V> m):
批量插入Map数据，

	 for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
	            put(e.getKey(), e.getValue());
	           
##### Set<K> keySet():
这个方法值得仔细分析，这个方法是返回map中的key的Set对象，并且可以看到keySet的定义为：

	    /**
     * Each of these fields are initialized to contain an instance of the
     * appropriate view the first time this view is requested.  The views are
     * stateless, so there's no reason to create more than one of each.
     */
    transient volatile Set<K>        keySet = null;
因此它是不可被序列化并且内存可见的。
而方法体如下：
	
	 public Set<K> keySet() {
	        if (keySet == null) {
	            keySet = new AbstractSet<K>() {
	                public Iterator<K> iterator() {
	                    return new Iterator<K>() {
	                        private Iterator<Entry<K,V>> i = entrySet().iterator();
	
	                        public boolean hasNext() {
	                            return i.hasNext();
	                        }
	
	                        public K next() {
	                            return i.next().getKey();
	                        }
	
	                        public void remove() {
	                            i.remove();
	                        }
	                    };
	                }
	
	                public int size() {
	                    return AbstractMap.this.size();
	                }
	
	                public boolean isEmpty() {
	                    return AbstractMap.this.isEmpty();
	                }
	
	                public void clear() {
	                    AbstractMap.this.clear();
	                }
	
	                public boolean contains(Object k) {
	                    return AbstractMap.this.containsKey(k);
	                }
	            };
	        }
	        return keySet;
	    }

可以看到方法中使用了匿名内部类，实现了AbstractKeySet<k>这个抽象类，并将产生的对象赋给了前面定义的keySet对象。在匿名内部类中，实现了几个基本的方法。这边的视线确实很巧妙，在AbstractKeySet中并没有明确的实现AbstractSet这个构造方法，而是等到在相关的方法中再实现。层次关系分的很清。
##### Collection<V> values() :
返回全部value值，与之前keySet类似不再赘述。
##### static class SimpleEntry<K,V> implements Entry<K,V>, java.io.Serializable:
在AbstractMap这个类中还创建了一个静态的内部类：SimpleEntry，这个类实现了Map接口中的内部接口：Entry。
##### static class SimpleImmutableEntry<K,V> implements Entry<K,V>, java.io.Serializable
同样AbstractMap这个类中还创建了一个静态的内部类：SimpleImmutableEntry，这个类实现了Map接口中的内部接口：Entry。这个匿名内部类不支持setvalue方法。即无法修改某个Entry的value值。
####HashMap
&emsp;&emsp;可以看到在HashMap中有几个内部类分别是：<br>
`Holder`,`Entry`,`HashIterator`,`ValueIterator`,`KeyIterator`,`EntryIterator`,`KeySet`,`Values`,`EntrySet`
阅读HashMap 源码从最简单的构造方法开始：
    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
