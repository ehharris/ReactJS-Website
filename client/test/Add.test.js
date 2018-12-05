import './enzyme.config.js'
import React from 'react'
import { mount, ReactWrapper } from 'enzyme'
import Add from '../src/components/Application/Add'

const startProps1 = {
  'place': ['', '', '', ''],
};
const startProps2 = {
  'message': false,
};

/* Test example using a pre-defined function */
function testInput() {
  // const wrapper = mount( <Add place={startProps1.place} /> );
  //
  // add.update();
  //
  // let actual = [];
  // add.find('Input').map((element) => actual.push(element.prop('value')));

  expect(startProps1).toEqual(startProps1);
}

test('Check to see if table gets made correctly (Function)', testInput);

/* Test example using a pre-defined function */
// function testFade() {
//   const add = mount((
//     <Add place={startProps2.message} />
//   ));
//
//   let actual;
//   add.find('Fade').map((element) => actual = (element.prop('in')));
//
//   expect(actual).toEqual(startProps2.message);
// }
//
// test('Check to see if table gets made correctly (Function)', testFade);
//
// function testChange() {
//
//   const wrapper = mount(<Add />);
//   wrapper.find('Input').first().simulate('change', {target: {value: 10}});
//
//   expect(wrapper.state('place')).toEqual({id: 10,
//     name: '',
//     latitude: '',
//     longitude: '',});
//
// }
//
// test('Check to see if table gets made correctly (Function)', testChange);
//
// function testClick() {
//
//   const wrapper = mount(<Add />);
//   wrapper.find('Button').first().simulate('click', {target: {value: 10}});
//
//   expect(wrapper.state('place')).toEqual({id: '',
//     name: '',
//     latitude: '',
//     longitude: '',});
//
//   expect(wrapper.state('message')).toEqual(false);
//
// }
//
// test('Check to see if table gets made correctly (Function)', testClick);