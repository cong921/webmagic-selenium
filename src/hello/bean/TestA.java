package hello.bean;

import org.hamcrest.core.IsInstanceOf;

public class TestA extends B {
public static void main(String[] args) {
	B testA = new B();
	System.out.println(testA instanceof TestA);
}
}
class B{}
