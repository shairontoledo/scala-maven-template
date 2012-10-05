package samples

import org.specs2.mock.Mockito
import org.specs2.matcher.MatchResult
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

/**
 * Sample specification.
 * 
 * This specification can be executed with: scala -cp <your classpath=""> ${package}.MySpec
 * Or using maven: mvn test
 *
 * For more information on how to write or run specifications, please visit: http://etorreborre.github.com/specs2/ .
 *
 */
@RunWith(classOf[JUnitRunner])
class MySpec extends Specification with Mockito{

  "MySpec" should {
    
    "do something ..." in { true must beTrue }
    
    //http://code.google.com/p/specs/wiki/UsingMockito
    "do something with mock" in {
      val spiedList = spy(new java.util.LinkedList[String])
      spiedList.size returns 100
      spiedList.add("one")
      spiedList.add("two") 
      there was one(spiedList).add("one")    
    }
    
    //For more matches http://etorreborre.github.com/specs2/guide/org.specs2.guide.Matchers.html
    "do something with Numerics" in {
      1 must be_<=(2)
      1 must beLessThanOrEqualTo(2)
    }
    
    "do something with Traversables" in {
      List(4, 2) must contain(2, 4).only
    }
    
  }
}
