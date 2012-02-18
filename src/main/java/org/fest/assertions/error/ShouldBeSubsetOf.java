/*
 * Created on Feb 18, 2012
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
 * Copyright @2012 the original author or authors.
 */
package org.fest.assertions.error;

import org.fest.util.*;

/**
 * Creates an error message indicating that an assertion that verifies that a <code>{@link Iterable}</code> is
 * a subset of an other set {@Iterable} failed.
 * 
 * @author Maciej Jaskowski
 */
public class ShouldBeSubsetOf extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeSubsetOf}</code>
   * @param actual the actual set
   * @param values the expected superset
   * @param comparisonStrategy the <code>{@link ComparisonStrategy}</code> used
   * @return the created <code>{@link ErrorMessageFactory}</code>
   */
  public static ErrorMessageFactory shouldBeSubsetOf(Object actual, Object values, ComparisonStrategy comparisonStrategy) {
    return new ShouldBeSubsetOf(actual, values, comparisonStrategy);
  }

  private ShouldBeSubsetOf(Object actual, Object values, ComparisonStrategy comparisonStrategy) {
    super("Expecting:<%s> to be subset of <%s>%s", actual, values, comparisonStrategy);
  }
}
