import './enzyme.config.js'
import React from 'react'
import { shallow } from 'enzyme'
import Application from '../src/components/Application/Application'

const startProps1 = {
  'place': [],
};
const startProps2 = {
  'message': false,
};

/* Test example using a pre-defined function */
function testInput() {
  const app = shallow((
    <Application place={startProps1.place} />
  ));

  let actual = [];
  app.find('Nav').map((element) => actual.push(element.prop('id')));

  expect(actual).toEqual(startProps1.place);
}

test('Check to see if table gets made correctly (Function)', testInput);