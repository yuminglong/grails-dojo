package org.dojotoolkit


class WidgetController {

  def index = { }
  
  def plainRemote = {}

  def testRemoteLink = {
    render {
      div(style:"border:3px solid orange; background:#eee; padding:5em;", "Remote Link - Passed.")
    }
  }
  
  def remotePostFromLink = {
    render {
      div(style:"border:3px solid blue; background:#eee; padding:5em;", "Remote Link with URL Map -  Passed.")
    }    
  }
  
  def remoteRequestWithUrlMap = {
    render {
      div(style:"border:3px solid red; background:#eee; padding:5em;", "Remote Request With Url Map - Passed.")
    }    
  }
  
  def synchronizedRemoteLink = {
    render {
      div(style:"border:3px solid green; background:#eee; padding:5em;", "Synchronized Remote Link - Passed.")
    } 
  }
  
  def remoteFunctionAction = {
    println "In Remote Function Action"
    println params
    render {
      div(style:"border:3px solid ${params?.color}; background:#eee; padding:5em;", "Remote Function - Passed.")
    }    
  }  
  
  def remoteFunctionWithParams = {
    render {
      div(style:"border:3px solid ${params?.color}; background:#eee; padding:5em;", "${params.pluginName} - Remote Function With Params - Passed.")
    }
  }
  
  def remoteField = {
    render {
      div(style:"border:3px solid ${params?.color}; background:#eee; padding:5em;", "Remote Field - Passed.")
    }
  }  
  
  def remoteJsFunction = {
    render {
      div(style:"border:3px solid pink; background:#eee; padding:5em;", "${params.name} Remote Function (from JS) - Passed.")
    }
  }  

  def remoteFormSubmit = {
    render {
      div(style:"border:3px solid green; background:#eee; padding:1em;", "${params} Remote Form Submit - Passed.")
    }
  }

  def remoteFormSubmitAsGet = {
    render {
      div(style:"border:3px solid red; background:#eee; padding:1em;", "${params} Remote Form Get - Passed.")
    }
  }

  def submitToRemoteButton = {
    render {
      div(style:"border:3px solid blue; background:#eee; padding:1em;", "${params} Submit To Remote Button - Passed.")
    }
  }


  def remoteDijitContent = {
    
  }

  def popOverForm = {
    
  }


  def list = {
    if (!params.max) params.max = 10
    [widgetList: Widget.list(params), total: Widget.count()]
  }

  def listFragment = {
    if (!params.max) params.max = 10
    render(template: "list", model: [widgetList: Widget.list(params), total: Widget.count()])
  }

  def dijits = {}
  def grid = {}
  def popOver = {}


  def listJson = {
    println params
    def widgets,widgetsTotal   
      
    if(params?.shape){
      widgets = Widget.findAllByShapeLike("%${params?.shape}%");
      widgetsTotal = widgets.size();
      widgets = Widget.findAllByShapeLike("%${params?.shape}%",params);
      println widgets.size();
    }
    else{
      widgetsTotal = Widget.list().size();      
      widgets = Widget.list(params)
    }
    render(contentType: "text/json") {
      identifier("id")
      numRows(widgetsTotal)
      items {
        widgets.each {w ->
          item(
            id:w.id,  
            name:w.name,
            color:w.color,
            shape:w.shape
          )  
        }
      }
    }
    
  }
}
