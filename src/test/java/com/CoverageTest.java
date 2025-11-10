package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 此测试文件旨在满足 add() 方法的分支条件覆盖。
 * 覆盖以下所有情况：
 * 1. 正常相加 (a >= 0, b >= 0, 不溢出)
 * 2. 整数溢出 (a >= 0, b >= 0, 溢出)
 * 3. 'a' 为负数 (a < 0)
 * 4. 'b' 为负数 (b < 0)
 * 5. 'a' 和 'b' 均为负数
 */
class CoverageTest {

	private Main main;

	@BeforeEach
	void setUp() {
		// 在每个测试运行前初始化计算器实例
		main = new Main();
	}

	/**
	 * 测试用例 1 (TC1): 正常相加 (覆盖分支 2 和 4)
	 * 条件: (a < 0) = False, (b < 0) = False, (Integer.MAX_VALUE - a < b) = False
	 */
	@Test
	void testNormalAddition() {
		// 您原来的测试用例 (1, 1) 属于这一类。我们使用 (5, 5) 作为另一个例子。
		int result = main.add(5, 5);
		assertEquals(10, result, "5 + 5 应该等于 10");
	}

	/**
	 * 测试用例 2 (TC2): 整数溢出 (覆盖分支 2 和 3)
	 * 条件: (a < 0) = False, (b < 0) = False, (Integer.MAX_VALUE - a < b) = True
	 */
	@Test
	void testIntegerOverflow() {
		// 测试 (Integer.MAX_VALUE, 1)
		Exception exception = assertThrows(ArithmeticException.class, () -> {
		main.add(Integer.MAX_VALUE, 1);
	}, "相加 Integer.MAX_VALUE 和 1 应该抛出 ArithmeticException");

		// 验证异常消息
		String expectedMessage = "Integer overflow";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage), "异常消息应包含 'Integer overflow'");
	}

	/**
	 * 测试用例 3 (TC3): 'a' 为负数 (覆盖分支 1)
	 * 条件: (a < 0) = True
	 */
	@Test
	void testNegativeA() {
		// 测试 (-1, 5)
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
		main.add(-1, 5);
	}, "当 'a' 为负数时应抛出 IllegalArgumentException");
		
		String expectedMessage = "Both numbers must be non-negative";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage), "异常消息应包含 'Both numbers must be non-negative'");
	}

	/**
	 * 测试用例 4 (TC4): 'b' 为负数 (覆盖分支 1)
	 * 条件: (a < 0) = False, (b < 0) = True
	 */
	@Test
	void testNegativeB() {
		// 测试 (5, -1)
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
		main.add(5, -1);
	}, "当 'b' 为负数时应抛出 IllegalArgumentException");
		
		String expectedMessage = "Both numbers must be non-negative";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage), "异常消息应包含 'Both numbers must be non-negative'");
	}

	/**
	 * 测试用例 5 (TC5): 'a' 和 'b' 均为负数 (覆盖分支 1)
	 * 条件: (a < 0) = True, (b < 0) = True (尽管 'a < 0' 会短路)
	 */
	@Test
	void testBothNegative() {
		// 测试 (-1, -1)
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
		main.add(-1, -1);
	}, "当 'a' 和 'b' 均为负数时应抛出 IllegalArgumentException");
		
		String expectedMessage = "Both numbers must be non-negative";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage), "异常消息应包含 'Both numbers must be non-negative'");
	}
}
