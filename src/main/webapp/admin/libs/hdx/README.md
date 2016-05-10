Healthcare Design Extension (HDx) README
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

This implementation of the Healthcare Design Extension (HDx) consists of:

- [**Twitter Bootstrap**](http://twitter.github.com/bootstrap/) for the overall page layout, scaffolding, and basic components
- [**GE Bootstrap**](http://devcloud.sw.ge.com/source/projects/DXC/repos/ge-bootstrap), an extension of Twitter Bootstrap supporting GE's brand
- [**Modernizr**](http://modernizr.com/) to detect HTML5 and CSS3 features in the user's browser
- [**jQuery**](http://jquery.com/) for DOM manipulation and general front-end utility use
- [**RequireJS+jQuery**](https://github.com/jrburke/require-jquery) for Javascript code modularity.

Release Distribution
--------------------
The HDx release distribution includes the following:

- `docs` contains all assets used by the HDx documentation pages
- `less` contains the HDx stylesheets, in LESS format
- `js` contains the master HDx application JavaScript and the configuration file used by RequireJquery

## Structure

- **README.md** — A starter readme file in Markdown format.
- **History.md** — A starter history/changelog file, also in Markdown format.
- **package.json** — An [npm](https://npmjs.org/) package file for specifying information and dependencies.
- **bower.json** — A [Bower](http://bower.io/) package file for specifying information and dependencies.
- **.bowerrc** — A configuration file for Bower which tells it to look for modules on our Stash server.
- **.jshintrc** — [JSHint](http://www.jshint.com/) configuration file for managing JavaScript code quality.
- **.editorconfig** — [EditorConfig](http://editorconfig.org/) configuration file for managing coding styles within IDEs.
- **docs/**- This folder contains documentation and examples of HDx layouts, elements, and components.
- **js/** — This folder contains a [RequireJS](http://requirejs.org/) config file.
- **less/** — This folder contains [LESS](http://lesscss.org/) stylesheets for basic and responsive styles. It also includes some variables.

## Browser Compatibility

The following browsers have been tested for compliance and compatibility:

- Internet Explorer 9 (Windows 7)
- Internet Explorer 10 (Windows 7)
- Chrome 27.0.x (Mac OS X 10.8.x & Windows 7)
- Firefox 21.x (Mac OS X 10.8.x & Windows 7)
- Safari 6.0.x (Mac OS X 10.8.x)
- Mobile Safari for iPad, landscape only (iOS 6)

## Migrating from 0.9.x/0.10.x to 1.0.0

- Update script and link tags to point at the components folders. It's probably easiest to just look at how it's done in the examples and copy it.

- Make sure RespondJS is at the bottom of the page. And uses the path <script src="./components/respond/respond.src.js"></script>

- common.js was renamed to require.config.js to clear up confusion.

- The combined version of jquery/requirejs is no longer used. Now require.js is loaded in a script tag and jquery is brought in as a dependency like all the other modules.

The HDx is very similar in structure to the IIDS. Therefore, [the IIDS Migration Document](https://devcloud.swcoe.ge.com/devspace/display/IIDS/Migrating+from+0.9.x+to+0.10.0) is a useful resource.

## Migrating from 1.0.0 to 1.2
## TODO

## Developing the HDx

To log a bug or request a feature please see the [HDx JIRA page](https://devcloud.swcoe.ge.com/tracker/browse/HDS).

© General Electric 
