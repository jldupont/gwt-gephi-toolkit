This project consists in a port of the Gephi Toolkit to GWT (Google Web Toolkit).

It is currently usable for graph manipulation & layout.

Major Changes
=============

* Swap "HashTable" for "HashMap"
* Swap "ConcurrentLinkedQueue" with "PriorityQueue"
* Removed "ViewDestructorThread" in "org.gephi.graph.dhns.core.GraphStructure"
* No multiple projects
* No multiple workspaces
* No project/workspace change listener
* org.openide.util.Lookup : alternative implementation
* org.gephi.graph.dhns.core.GraphViewImpl: swapped "WeakHashMap" for "HashMap"
* java.util.Arrays.copyOf --> use "for" loop
* java.util.concurrent.atomic.AtomicInteger: alternative implementation non-thread-safe
* removed dependency on gnu.trove
* java.util.GregorianCalendar : removed
* java.beans.PropertyEditor: removed
* org.openide.nodes.PropertySupport: removed
* org.openide.nodes.Node.Property: removed
* no layout spi
* removed all locking/unlocking
* removed eventing system


TODO
====

* Use GWT emulation more to improve ease of tracking the upstream project
* Re-introduce "change event listener" facility 
