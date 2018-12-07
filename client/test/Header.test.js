import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Header from '../src/components/Marginals/Header.js'


const startProps = {
  'type': [undefined, "add-header-height", "application-width", undefined, undefined, "add-title"],
};

/* Test example using a pre-defined function */
function testInput() {
  const head = shallow((
    <Header />
  ));

  let actual = [];
  head.find('div').map((element) => actual.push(element.prop('className')));
  expect(actual).toEqual(startProps.type);
}

test('Check to see if table gets made correctly (Function)', testInput);