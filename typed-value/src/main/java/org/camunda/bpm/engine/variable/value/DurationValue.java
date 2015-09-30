/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.variable.value;

/**
 * A {@link TypedValue} that represents a duration. A duration is defined as an
 * amount of years and months or days, hours, minutes and seconds. The
 * combination of years/months and days/hours/minutes/seconds is not allowed
 * because year and month have no fixed size that can represented as amount of
 * seconds.
 *
 * @author Philipp Ossler
 * @since 7.4
 */
public interface DurationValue extends TypedValue {

  /**
   * Returns a number that represents the duration. The number have different
   * meanings depends on the unit. In case the unit is
   * {@link DurationValueUnit#DayTime} then the number represents the amount of
   * seconds. Otherwise, the number represents the amount of months.
   *
   * @see #getUnit()
   */
  @Override
  Long getValue();

  /**
   * Returns the unit of the value.
   *
   * @see #getValue()
   */
  DurationValueUnit getUnit();

}
