import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Navigation from '../src/components/Marginals/Navigation.js'


const startProps = {
  'type': ["application-width", undefined, undefined, undefined, undefined],
};

/* Test example using a pre-defined function */
function testInput() {
  const nav = shallow((
    <Navigation />
  ));

  let actual = [];
  nav.find('div').map((element) => actual.push(element.prop('className')));
  expect(actual).toEqual(startProps.type);
}

test('Check to see if table gets made correctly (Function)', testInput);