/*
 * Created on Nov 22, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.error.ShouldBeSubsetOf.*;
import static org.fest.util.Arrays.array;
import static org.fest.util.Collections.list;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.List;

import org.fest.assertions.core.AssertionInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests for <code>{@link Iterables#assertIsSubsetOf(AssertionInfo, Collection, Object[])}</code>.
 * 
 * @author Maciej Jaskowski
 */
public class Iterables_assertIsSubsetOf_Test extends AbstractTest_for_Iterables {

  @Override
  @Before
  public void setUp() {
    super.setUp();
    actual = list("Yoda", "Luke");
  }

  @Test
  public void should_pass_if_actual_is_subset_of_set() {
	  iterables.assertIsSubsetOf(someInfo(), actual, list("Luke", "Yoda", "Obi-Wan"));
  }
  
  @Test
  public void should_pass_if_actual_has_the_same_elements_as_set() {
	  iterables.assertIsSubsetOf(someInfo(), actual, list("Luke", "Yoda"));
  }
  
  @Test
  public void should_pass_if_actual_is_empty() {
	  actual = list();
	  iterables.assertIsSubsetOf(someInfo(), actual, list("Luke", "Yoda"));
  }
  
  @Test
  public void should_pass_if_actual_and_set_are_both_empty() {
	  actual = list();
	  iterables.assertIsSubsetOf(someInfo(), actual, list());
  }
  
  @Test
  public void should_pass_if_actual_has_duplicates_but_all_elements_are_in_values() {
	  actual = list("Yoda", "Yoda");
	  iterables.assertIsSubsetOf(someInfo(), actual, list("Yoda"));
  }
  
  @Test
  public void should_pass_if_values_has_duplicates_but_all_elements_are_in_values() {
	  actual = list("Yoda", "C-3PO");
	  iterables.assertIsSubsetOf(someInfo(), actual, list("Yoda", "Yoda", "C-3PO"));
  }
  
  @Test
  public void should_pass_if_both_actual_and_values_have_duplicates_but_all_elements_are_in_values() {
	  actual = list("Yoda", "Yoda", "Yoda", "C-3PO", "Obi-Wan");
	  iterables.assertIsSubsetOf(someInfo(), actual, list("Yoda", "Yoda", "C-3PO", "C-3PO", "Obi-Wan"));
  }
  
  @Test
  public void should_throw_error_if_set_is_null() {
	  thrown.expectNullPointerException(valuesToLookForIsNull());
	  iterables.assertIsSubsetOf(someInfo(), actual, null);
  }
  
  @Test
  public void should_throw_error_if_actual_is_null() {
	  thrown.expectAssertionError(actualIsNull());
	  iterables.assertIsSubsetOf(someInfo(), null, list());
  }
  
  
  //duplicates in actual ; duplicates in values; duplicates in both

  // ------------------------------------------------------------------------------------------------------------------
  // tests using a custom comparison strategy
  // ------------------------------------------------------------------------------------------------------------------

  @Test
  public void should_pass_if_actual_is_subset_of_values_according_to_custom_comparison_strategy() {
    iterablesWithCaseInsensitiveComparisonStrategy.assertIsSubsetOf(someInfo(), actual, list("yoda", "lUKE" ));
  }

  @Test
  public void should_pass_if_actual_contains_duplicates_according_to_custom_comparison_strategy() {
    actual = list("Luke", "Luke");
    iterablesWithCaseInsensitiveComparisonStrategy.assertIsSubsetOf(someInfo(), actual, list("LUke", "yoda"));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_even_if_duplicated_according_to_custom_comparison_strategy() {
    iterablesWithCaseInsensitiveComparisonStrategy.assertContains(someInfo(), actual, array("LUke", "LuKe", "yoda"));
  }
  
  @Test
  public void should_fail_if_actual_is_not_subset_of_values_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    List<String> values = list("yoda", "C-3PO" );
    try {
      iterablesWithCaseInsensitiveComparisonStrategy.assertIsSubsetOf(info, actual, values);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeSubsetOf(actual, values, comparisonStrategy));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }


}
