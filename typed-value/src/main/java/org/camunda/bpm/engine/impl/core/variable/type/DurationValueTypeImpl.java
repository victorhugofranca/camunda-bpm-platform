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
package org.camunda.bpm.engine.impl.core.variable.type;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.type.DurationValueType;
import org.camunda.bpm.engine.variable.value.DurationValue;
import org.camunda.bpm.engine.variable.value.DurationValueUnit;
import org.camunda.bpm.engine.variable.value.TypedValue;

/**
 * @author Philipp Ossler
 * @since 7.4
 */
public class DurationValueTypeImpl extends AbstractValueTypeImpl implements DurationValueType {

  private static final long serialVersionUID = 1L;

  public static final String TYPE_NAME = "duration";
  public static final String VALUE_INFO_UNIT = "unit";

  public DurationValueTypeImpl() {
    super(TYPE_NAME);
  }

  @Override
  public boolean isPrimitiveValueType() {
    return true;
  }

  @Override
  public Map<String, Object> getValueInfo(TypedValue typedValue) {
    if (!(typedValue instanceof DurationValue)) {
      throw new IllegalArgumentException("Value should be of type DurationValue");
    }
    DurationValue durationValue = (DurationValue) typedValue;

    Map<String, Object> valueInfo = new HashMap<String, Object>();
    valueInfo.put(VALUE_INFO_UNIT, durationValue.getUnit().name());

    return valueInfo;
  }

  @Override
  public TypedValue createValue(Object value, Map<String, Object> valueInfo) {
    if (!(value instanceof Long)) {
      throw new IllegalArgumentException("Duration value should be of type Long.");
    }

    return Variables.durationValue((Long) value).unit(getUnit(valueInfo)).create();
  }

  protected DurationValueUnit getUnit(Map<String, Object> valueInfo) {
    if (valueInfo.containsKey(VALUE_INFO_UNIT)) {
      String unit = valueInfo.get(VALUE_INFO_UNIT).toString();

      try {
        return DurationValueUnit.valueOf(unit);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("'" + unit + "' is not supported as unit of duration.");
      }
    } else {
      throw new IllegalArgumentException("The unit of the duration must be set in value info.");
    }
  }

}
