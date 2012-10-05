
Maven template project with common used librares(by me) based on <https://github.com/davidB/scala-archetype-simple> by [David Bernarde](https://github.com/davidB).

In the target project includes:

- org.scala-lang - 2.9.1
- junit - 4.10
- org.specs2 - 1.9 for scala version 2.9.1
- org.mockito - 1.9.0
- ch.qos.logback - 1.0.7
- <package>/App - App sample file
- test/samples/MySpec - Spec2 with Mockito sample file


## Install

You need install it once in your local archtype repository, just clone the project and install it via maven install

    git clone git://github.com/shairontoledo/scala-maven-template.git
    cd scala-maven-template
    mvn install 
    
Above commands will register scala-maven-template at `~/.m2/archetype-catalog.xml`

## Generating target template project

The archtype will ask you `groupId`, `artifactId`, `version` and `package`, so fire this line

    mvn archetype:generate -DarchetypeGroupId=org.scala-tools.archetypes -DarchetypeArtifactId=scala-maven-template
    
You will see something like

    [INFO] Generating project in Interactive mode
    [INFO] Archetype [org.scala-tools.archetypes:scala-maven-template:1.0] found in catalog local
    Define value for property 'groupId': : net.myprojects
    Define value for property 'artifactId': : foobar
    Define value for property 'version':  1.0-SNAPSHOT: : 
    Define value for property 'package':  net.myprojects: : 
    Confirm properties configuration:
    groupId: net.myprojects
    artifactId: foobar
    version: 1.0-SNAPSHOT
    package: net.myprojects
     Y: : y

# Tests 

Running tests with Spec2 and Maven compatibility(junit) you need add `@RunWith(classOf[JUnitRunner])` above your `testSpec` like this example generate by archtype:


    package samples

    import org.specs2.mock.Mockito
    import org.specs2.matcher.MatchResult
    import org.specs2.mutable.Specification
    import org.specs2.runner.JUnitRunner
    import org.junit.runner.RunWith
    
    
    @RunWith(classOf[JUnitRunner])
    class MySpec extends Specification with Mockito{

      "MySpec" should {
    
        "do something ..." in { true must beTrue }

        "do something with mock" in {
          val spiedList = spy(new java.util.LinkedList[String])
           spiedList.size returns 100
           spiedList.add("one")
           spiedList.add("two") 
           there was one(spiedList).add("one")    
        }
    
        "do something with Numerics" in {
          1 must be_<=(2)
          1 must beLessThanOrEqualTo(2)
        }
    
        "do something with Traversables" in {
          List(4, 2) must contain(2, 4).only
        }
    
      }
    }

And so run tests

    mvn test
    
Or just that file 

    mvn test -Dtest=samples.MySpec
    
## Run App.scala

    mvn exec:java -Dexec.mainClass=net.myprojects.App
    



