import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Plan from '../src/components/Application/Plan'


const startProps = {
  'type': ['button'],
  'label': ['labelpre', 'labelpre', 'labelpop'],
};

/* Test example using a pre-defined function */
function testInput() {
  const plan = mount((
    <Plan/>
  ));

  let actual = [];
  plan.find('Button').map((element) => actual.push(element.prop('type')));
  expect(actual).toEqual(startProps.type);
}

test('Check to see if table gets made correctly (Function)', testInput);


// function testChange() {
//
//   // describe what we are testing
//   // expect(shallow(<Calculator/>).find('Input').first().simulate())
//
//   const wrapper = mount(<Calculator />);
//   wrapper.find('Input').first().simulate('change', {target: {value: 10}});
//
//   expect(wrapper.state('distance')).toEqual({"destination": {"latitude": 0, "longitude": 0}, "distance": 0, "origin": {"latitude": 10, "longitude": 0}, "type": "distance", "units": "miles", "version": "4"});
//
// }
//
// test('Check to see if table gets made correctly (Function)', testChange);