package org.dojotoolkit
import org.dojotoolkit.TagLibUtil as Util


class EditorTagLib {
  static namespace = 'dojo'

  /**
   * Outputs the required javascript dojo libraries
   */
  def editorResources = {attrs,body ->
    out << dojo.require(modules:['dijit.Editor','dijit._editor.plugins.LinkDialog','dijit._editor.plugins.FontChoice'])
  }



  /**
   * Creates a dojo rich text editor with default values chosen.
   */
  def editor = {attrs, body ->
    attrs.height = attrs?.height ?: "150px"
    attrs.class =  (attrs?.class) ? "${attrs?.class} DojoUiEditor" : "DojoUiEditor"
    attrs.id = attrs.remove("elementId") ?: attrs?.id ?: attrs.remove("name") ?: "dojo_editor_${Util.randomId()}"
    def value = attrs.remove("value") ?: body()

    /*
      HACK - RM (12-20-2011)
      Removing the 'dijit._editor.plugins.EnterKeyHandling' plugin because of these bugs:
      http://bugs.dojotoolkit.org/ticket/13399
      http://bugs.dojotoolkit.org/ticket/13744
     */
    def defaultPlugins = """
      ['bold','italic','underline','|', 'insertOrderedList','insertUnorderedList','indent','outdent','|',
      'justifyLeft','justifyCenter', '|', 'createLink', '|', {name:'fontSize', plainText: true}]
    """
    attrs.plugins = attrs?.plugins ?: defaultPlugins

    out << """
      <fieldset class="dojo-ui-editor">
        <div dojoType="dijit.Editor" ${Util.htmlProperties(attrs)}>
          <script type="dojo/connect" event="onDisplayChanged">
            dojo.byId('${attrs?.id}').value = this.attr('value')
          </script>
          ${value}
        </div>
      </fieldset>
    """
  }

}
