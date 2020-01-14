package com.example.googlearcoresceneform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class MainActivity : AppCompatActivity() {

    private var arrayView = ArrayList<ImageView>()
    private lateinit var arFragment:ArFragment
    private lateinit var dragonRenderable:ModelRenderable
    private lateinit var dragon:ImageView
    private var selected = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arFragment = supportFragmentManager.findFragmentById(R.id.sceneform_ux_fragment) as ArFragment
        setArrayView()
        setOnClickListeners()
        setupModel()

        arFragment.setOnTapArPlaneListener{ hitResult: HitResult?, plane: Plane?, motionEvent: MotionEvent? ->
            val anchor=hitResult?.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)
            createModel(anchorNode,selected)
        }
    }

    private fun setupModel() {
        ModelRenderable.builder().setSource(this,)
    }

    private fun createModel(anchorNode: AnchorNode, selected: Int) {
        if (selected == 1){
            val dragonTransformableNode = TransformableNode(arFragment.transformationSystem)
            dragonTransformableNode.setParent(anchorNode)
            dragonTransformableNode.renderable = dragonRenderable
            dragonTransformableNode.select()

        }
    }

    private fun setOnClickListeners() {
        for (view in arrayView){
            view.setOnClickListener{

            }
        }
    }

    private fun setArrayView() {
        dragon = findViewById(R.id.dragon1)
        arrayView.add(dragon)
    }
}
